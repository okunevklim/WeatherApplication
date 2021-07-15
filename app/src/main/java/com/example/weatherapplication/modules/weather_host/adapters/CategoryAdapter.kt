package com.example.weatherapplication.modules.weather_host.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapplication.modules.weather_today.TodayFragment
import com.example.weatherapplication.modules.weather_week.WeekFragment

class CategoryAdapter(activity: FragmentActivity, private val city: String) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return COUNT_NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TodayFragment(city)
            1 -> WeekFragment(city)
            else -> throw IllegalArgumentException("Invalid position=$position")
        }
    }

    companion object {
        const val COUNT_NUMBER = 2
    }
}