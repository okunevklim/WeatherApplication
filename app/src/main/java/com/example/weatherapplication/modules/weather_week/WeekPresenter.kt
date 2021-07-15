package com.example.weatherapplication.modules.weather_week

import android.annotation.SuppressLint
import android.widget.TextView
import com.example.weatherapplication.modules.weather_week.utils.DateFormatter
import com.example.weatherapplication.shared.base.WeatherApplication
import com.example.weatherapplication.shared.di.module.Api
import com.example.weatherapplication.shared.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class WeekPresenter : MvpPresenter<WeekView>() {

    @Inject
    lateinit var api: Api

    init {
        WeatherApplication.component.inject(this)
    }

    @SuppressLint("CheckResult")
    fun getWeeklyTemperature(
        cityName: String,
        degreeViews: Array<TextView>,
        dayViews: Array<TextView>
    ) {
        api.getService().getWeeklyTemperature(cityName, Constants.API_KEY)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.handleError(false, degreeViews, dayViews)
                it.list?.let { listOfTemperature ->
                    degreeViews.forEachIndexed { index, view ->
                        val temperatureInKelvin =
                            listOfTemperature[index].main?.temp?.minus(Constants.KELVIN_DEGREE)
                        val temperatureInDegree =
                            String.format(Constants.TEMPERATURE_PATTERN, temperatureInKelvin)
                        view.text = temperatureInDegree
                    }
                    var dayViewIndex = 0
                    for (v in 0 until listOfTemperature.size step 9) {
                        val dayOfWeek = DateFormatter.formatDate(listOfTemperature[v].dt ?: 0)
                        dayViews[dayViewIndex].text = dayOfWeek
                        dayViewIndex += 1
                    }
                }
            }
            .doOnError { viewState.handleError(true, degreeViews, dayViews) }
            .subscribe()
    }
}