package com.example.weatherapplication.modules.weather_today

import com.example.weatherapplication.shared.base.WeatherApplication
import com.example.weatherapplication.shared.di.module.Api
import com.example.weatherapplication.shared.utils.Constants
import com.example.weatherapplication.shared.utils.Constants.API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TodayPresenter : MvpPresenter<TodayView>() {

    @Inject
    lateinit var api: Api

    init {
        WeatherApplication.component.inject(this)
    }

    fun getCurrentTemperature(cityName: String) {
        api.getService().getCurrentTemperature(cityName, API_KEY)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.handleError(false)
                val temperature = it.main?.temp?.minus(Constants.KELVIN_DEGREE)
                val temperatureInDegree = String.format(Constants.TEMPERATURE_PATTERN, temperature)
                viewState.handleTemperature(temperatureInDegree)
            }
            .doOnError { viewState.handleError(true) }
            .subscribe()
    }
}