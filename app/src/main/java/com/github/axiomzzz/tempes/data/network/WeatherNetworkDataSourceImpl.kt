package com.github.axiomzzz.tempes.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce
import com.github.axiomzzz.tempes.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl (
    private val apiService: ApiService
): WeatherNetworkDataSource {



    private val _downloadedCurrentWeather=MutableLiveData<WeatherResponce>()

    override val downloadedCurrentWeather: LiveData<WeatherResponce>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, country: String) {
        try {
            val fetchCurrentWeather=apiService.getCurrentWeather(location,country)

            _downloadedCurrentWeather.value=fetchCurrentWeather // !!!!!!!!!!!!!!! postValue????

        }catch (e:NoConnectivityException){

        }
    }
}