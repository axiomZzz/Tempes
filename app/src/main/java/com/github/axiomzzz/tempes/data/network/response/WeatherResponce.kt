package com.github.axiomzzz.tempes.data.network.response


import com.github.axiomzzz.tempes.data.db.entity.Data
import com.google.gson.annotations.SerializedName

data class WeatherResponce(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`:List<Data>  // Data????????
)