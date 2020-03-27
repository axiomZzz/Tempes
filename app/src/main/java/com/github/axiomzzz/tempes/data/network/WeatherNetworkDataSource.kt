package com.github.axiomzzz.tempes.data.network

import androidx.lifecycle.LiveData
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce



interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<WeatherResponce> // Live Data ??
   //val downloadedFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        country: String
    )
//    suspend fun fetchFutureWeather(
//        location: String,
//        languageCode: String
//    )
}