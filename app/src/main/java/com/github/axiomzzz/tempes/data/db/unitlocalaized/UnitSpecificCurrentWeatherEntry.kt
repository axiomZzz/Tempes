package com.github.axiomzzz.tempes.data.db.unitlocalaized


interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
//    val feelsLikeTemperature: Double
//    val visibilityDistance: Double
}