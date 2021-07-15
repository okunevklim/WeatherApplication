package com.example.weatherapplication.shared.network

import com.example.weatherapplication.shared.models.WeatherResponse
import com.example.weatherapplication.shared.models.WeeklyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getCurrentTemperature(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Single<WeatherResponse>

    @GET("/data/2.5/forecast")
    fun getWeeklyTemperature(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Single<WeeklyResponse>
}