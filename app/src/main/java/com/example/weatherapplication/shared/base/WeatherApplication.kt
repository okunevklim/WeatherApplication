package com.example.weatherapplication.shared.base

import android.app.Application
import android.util.Log
import com.example.weatherapplication.shared.di.component.ApiComponent
import com.example.weatherapplication.shared.di.component.DaggerApiComponent
import io.reactivex.plugins.RxJavaPlugins

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
        RxJavaPlugins.setErrorHandler { Log.e("WeatherProject", "Error is ${it.message}") }
    }

    private fun buildComponent(): ApiComponent {
        return DaggerApiComponent.builder().build()
    }

    companion object {
        lateinit var component: ApiComponent
    }
}