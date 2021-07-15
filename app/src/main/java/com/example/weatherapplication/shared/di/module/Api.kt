package com.example.weatherapplication.shared.di.module

import androidx.annotation.NonNull
import androidx.viewbinding.BuildConfig
import com.example.weatherapplication.shared.network.WeatherService
import com.example.weatherapplication.shared.utils.Constants.SERVER_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
class Api @Inject constructor() {
    lateinit var serviceWeather: WeatherService
    var gson = Gson()

    private fun init() {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        serviceWeather = retrofit.create(WeatherService::class.java)
    }

    @Provides
    @NonNull
    @Singleton
    fun getService(): WeatherService {
        if (!this::serviceWeather.isInitialized) {
            init()
        }
        return serviceWeather
    }
}