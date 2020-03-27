package com.github.axiomzzz.tempes.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.github.axiomzzz.tempes.data.db.CurentWeatherDao
import com.github.axiomzzz.tempes.data.db.unitlocalaized.UnitSpecificCurrentWeatherEntry
import com.github.axiomzzz.tempes.data.network.ApiService
import com.github.axiomzzz.tempes.data.network.WeatherNetworkDataSource
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ForecastRepositoryImpl (
    private val currentWeatherDao: CurentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource

): ForecastRepository {

    override fun foo(fetchedWeather: WeatherResponce){
      persistFetchedCurrentWeather(fetchedWeather)

    }


    init {


        weatherNetworkDataSource.apply {

            downloadedCurrentWeather.observeForever {
                // if(it==null) return@observeForever
              persistFetchedCurrentWeather(it)
            }
//            downloadedFutureWeather.observeForever { newFutureWeather ->
//                persistFetchedFutureWeather(newFutureWeather)
//            }
        }


    }

    override suspend fun getCurrentWeather(): LiveData<out UnitSpecificCurrentWeatherEntry> {
        Log.i("AAAA","Hello get")
       return withContext(Dispatchers.IO){
           return@withContext currentWeatherDao.getWeatherMetric()
       }
    }

     private fun persistFetchedCurrentWeather(fetchedWeather: WeatherResponce) {

        Log.i("AAAA","Upsert Data base")

        // withContext or GlobalScope?????
       GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.data)
            //weatherLocationDao.upsert(fetchedWeather.location)
        }
    }
}