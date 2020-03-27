package com.github.axiomzzz.tempes.data.db.unitlocalaized

import androidx.room.ColumnInfo


data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "appTemp")
    override val temperature: Double,
    @ColumnInfo(name = "weather_description")
    override val conditionText: String,
    @ColumnInfo(name = "weather_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windSpd")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precip")
    override val precipitationVolume: Double
//    @ColumnInfo(name = "feelslikeC")
//    override val feelsLikeTemperature: Double,
//    @ColumnInfo(name = "visKm")
//    override val visibilityDistance: Double
) : UnitSpecificCurrentWeatherEntry