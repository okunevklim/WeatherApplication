package com.example.weatherapplication.modules.weather_week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.weatherapplication.databinding.FragmentWeekBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class WeekFragment(private val city: String) : MvpAppCompatFragment(), WeekView {

    private lateinit var binding: FragmentWeekBinding
    private val presenter by moxyPresenter { WeekPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val degreeViews =
            with(binding) { arrayOf(dayTemp1, dayTemp2, dayTemp3, dayTemp4, dayTemp5) }
        val daysViews = with(binding) { arrayOf(day1, day2, day3, day4, day5) }
        binding.cityName.text = city
        binding.errorLayout.retry.setOnClickListener {
            presenter.getWeeklyTemperature(city, degreeViews, daysViews)
        }
        presenter.getWeeklyTemperature(city, degreeViews, daysViews)
    }

    override fun handleError(
        isErrorVisible: Boolean,
        degreeViews: Array<TextView>,
        daysViews: Array<TextView>
    ) {
        binding.cityName.isVisible = !isErrorVisible
        degreeViews.forEach { it.isVisible = !isErrorVisible }
        daysViews.forEach { it.isVisible = !isErrorVisible }
        binding.errorLayout.root.isVisible = isErrorVisible
    }
}