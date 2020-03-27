package com.github.axiomzzz.tempes.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.axiomzzz.tempes.R
import com.github.axiomzzz.tempes.data.network.ApiService
import com.github.axiomzzz.tempes.data.network.WeatherNetworkDataSource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : Fragment() {

    private val model:CurrentWeatherViewModel by  viewModel()
    //private val model by viewModel<CurrentWeatherViewModel>()

//    private val forecastRepository:ForecastRepository by inject()   // by inject()!!!!!!!!!!!!!!
      private val apiService:ApiService by inject()
      private val  weatherNetworkDataSource:WeatherNetworkDataSource by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       // val apiService= ApiService(ConnectivityInterceptorImpl(this.context!!))
        //val weatherNetworkDataSource=WeatherNetworkDataSourceImpl(apiService)

//        GlobalScope.launch (Dispatchers.Main){
//            weatherNetworkDataSource.fetchCurrentWeather("Dallas","US")
//
//            weatherNetworkDataSource.downloadedCurrentWeather.observeForever {
//
//                model.forecastRepository.foo(it)   /// ????????????????????????
//            }
//        }

        bindUi()

    }

    private fun bindUi(){
        // viewLifecycleOwner !!!!!!!!!
        model.weather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer /// !!!!

            group_loading.visibility=View.GONE

            updateDateToToday()
            updateLocation("Samara")
            updateTemperatures(it.temperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)


            Picasso.get()
                .load("https://www.weatherbit.io/static/img/icons/${it.conditionIconUrl}.png")
//                .apply {
//
//                    this.placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)}
                .resize(100, 100)
                .centerCrop()
                .into(imageView_condition_icon)


        })

    }

    private fun updateLocation(location:String){
        (activity as AppCompatActivity).supportActionBar?.title=location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }



    private fun updateTemperatures(temperature: Double) {

        textView_temperature.text = "$temperature°C"
       // textView_feels_like_temperature.text = "Feels like $feelsLike°C"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {

        textView_precipitation.text = "Давление: $precipitationVolume mm"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {

        textView_wind.text = "Ветер: $windDirection, $windSpeed kph"
    }


}
