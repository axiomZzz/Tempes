package com.github.axiomzzz.tempes.data.repository

import androidx.lifecycle.LiveData
import com.github.axiomzzz.tempes.data.db.unitlocalaized.UnitSpecificCurrentWeatherEntry
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce


interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<out UnitSpecificCurrentWeatherEntry>
    fun foo(fetchedWeather: WeatherResponce) //// ??????

//    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>
//
//    suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>
//
//    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}