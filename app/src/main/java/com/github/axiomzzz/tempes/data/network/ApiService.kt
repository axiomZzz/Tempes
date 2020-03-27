package com.github.axiomzzz.tempes.data.network

import com.github.axiomzzz.tempes.BuildConfig
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = BuildConfig.TMDB_API_KEY
const val BASE_URL="https://api.weatherbit.io/v2.0/"

interface ApiService {

   // https://api.weatherbit.io/v2.0/current?city=Raleigh,NC&key=API_KEY

//    @Headers("key: $API_KEY")
    @GET("current")  // .json not need
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("lang") lang: String="ru"

    ): WeatherResponce   // no Deferred

    companion object{

        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiService {

             val authInterceptor = Interceptor {
                val newUrl = it.request().url
                    .newBuilder()
                    .addQueryParameter("key",
                        API_KEY
                    )
                    .build()

                val newRequest = it.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY   // Working only with Debug mode and Advanced Logging (Allow all) in Meizu N5 (LOG http..)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor) // WARNING!!!
                .addInterceptor(authInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())  // not need with suspend fun in Api
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)



        }
    }

}