package com.example.weatherapplication.shared.di.component

import com.example.weatherapplication.shared.di.module.Api
import com.example.weatherapplication.modules.weather_today.TodayPresenter
import com.example.weatherapplication.modules.weather_week.WeekPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Api::class])
interface ApiComponent {
    fun inject(todayPresenter: TodayPresenter)
    fun inject(weekPresenter: WeekPresenter)
}