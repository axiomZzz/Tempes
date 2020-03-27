package com.github.axiomzzz.tempes.ui.weather.current

import android.util.Log
import androidx.lifecycle.*
import com.github.axiomzzz.tempes.data.db.unitlocalaized.UnitSpecificCurrentWeatherEntry
import com.github.axiomzzz.tempes.data.network.WeatherNetworkDataSource
import com.github.axiomzzz.tempes.data.network.response.WeatherResponce
import com.github.axiomzzz.tempes.data.repository.ForecastRepository
import kotlinx.coroutines.*

class CurrentWeatherViewModel(
     private val forecastRepository: ForecastRepository,
     private val  weatherNetworkDataSource: WeatherNetworkDataSource
) : ViewModel() {

    init {
        Log.i("AAAA","Hello model")
        getWeather()
    }

    private val _weather=MutableLiveData<UnitSpecificCurrentWeatherEntry>()

     val weather: LiveData<UnitSpecificCurrentWeatherEntry>
        get() = _weather



    private fun getWeather(){
        viewModelScope.launch {
            weatherNetworkDataSource.fetchCurrentWeather("York","US")

//            weatherNetworkDataSource.downloadedCurrentWeather.observeForever {
//
//                forecastRepository.foo(it)   /// ????????????????????????
//            }

            forecastRepository.getCurrentWeather().observeForever {
                _weather.value=it
            }
        }
    }


//    val weather by lazyDeferred {
//        forecastRepository.getCurrentWeather()
//    }
//
//}
//
//fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
//    return lazy {
//        GlobalScope.async(start = CoroutineStart.LAZY) {
//            block.invoke(this)
//        }
//    }
}




