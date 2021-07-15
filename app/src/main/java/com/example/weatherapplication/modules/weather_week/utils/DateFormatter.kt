package com.example.weatherapplication.modules.weather_week.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private const val MILLISECONDS = 1000

    /**
     * @return formatted day of week, e.g. "Thursday"
     */
    fun formatDate(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = MILLISECONDS * date
        val df = SimpleDateFormat("cccc", Locale.ENGLISH)
        return df.format(calendar.time)
    }
}