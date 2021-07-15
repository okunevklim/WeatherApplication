package com.example.weatherapplication.modules.weather_today

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface TodayView : MvpView {
    fun handleError(isErrorVisible: Boolean)
    fun handleTemperature(temperature: String)
}