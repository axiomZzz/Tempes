package com.github.axiomzzz.tempes.di


import android.app.Application
import android.content.Context
import com.github.axiomzzz.tempes.data.db.ForecastDatabase
import com.github.axiomzzz.tempes.data.network.*
import com.github.axiomzzz.tempes.data.repository.ForecastRepository
import com.github.axiomzzz.tempes.data.repository.ForecastRepositoryImpl
import com.github.axiomzzz.tempes.ui.weather.current.CurrentWeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {

//  single <HelloRepository>{ HelloRepositoryImp() }

//  single { ForecastDatabase(androidContext()) }
  single { ForecastDatabase(androidContext()).currentWeatherDao()}
  single <ConnectivityInterceptor>{ ConnectivityInterceptorImpl(androidContext()) }
  single { ApiService(get()) }
  single <WeatherNetworkDataSource>{WeatherNetworkDataSourceImpl(get())}
  single <ForecastRepository>{ForecastRepositoryImpl(get(),get())}

  viewModel { CurrentWeatherViewModel(get(),get()) }


  //single{ Base() }

//    viewModel { MyViewModel(get()) }
//
//    factory { MySimplePresenter(get()) }

   // factory { HelloRepositoryImp(get ()) }
}
//class ForecastApplication : Application(), KodeinAware {
//  override val kodein = Kodein.lazy {
//    import(androidXModule(this@ForecastApplication))
//
//    bind() from singleton { ForecastDatabase(instance()) }
//    bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
//    bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
//    bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
//    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
//    bind() from singleton { ApixuWeatherApiService(instance()) }
//    bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
//    bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
//    bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
//    bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
//    bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
//    bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
//    bind() from provider { FutureListWeatherViewModelFactory(instance(), instance()) }
//    bind() from factory { detailDate: LocalDate -> FutureDetailWeatherViewModelFactory(detailDate, instance(), instance()) }
//  }