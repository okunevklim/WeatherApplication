package com.example.weatherapplication.shared.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeeklyResponse(val list: ArrayList<WeatherResponse>?) : Serializable

data class WeatherResponse(
    val dt: Long?,
    val main: TemperatureInfo?,
    @SerializedName("dt_txt")
    val dtTxt: String?
) : Serializable

data class TemperatureInfo(val temp: Double) : Serializable