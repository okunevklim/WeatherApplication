package com.example.weatherapplication.modules.weather_week

import android.widget.TextView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface WeekView : MvpView {
    fun handleError(
        isErrorVisible: Boolean,
        degreeViews: Array<TextView>,
        daysViews: Array<TextView>
    )
}