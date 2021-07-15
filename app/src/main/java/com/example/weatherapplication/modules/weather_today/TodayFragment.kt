package com.example.weatherapplication.modules.weather_today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.weatherapplication.databinding.FragmentTodayBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TodayFragment(private val city: String) : MvpAppCompatFragment(), TodayView {

    private lateinit var binding: FragmentTodayBinding
    private val presenter by moxyPresenter { TodayPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cityName.text = city
        binding.errorLayout.retry.setOnClickListener { presenter.getCurrentTemperature(city) }
        presenter.getCurrentTemperature(city)
    }

    override fun handleError(isErrorVisible: Boolean) {
        binding.cityName.isVisible = !isErrorVisible
        binding.cityTemperature.isVisible = !isErrorVisible
        binding.errorLayout.root.isVisible = isErrorVisible
    }

    override fun handleTemperature(temperature: String) {
        binding.cityTemperature.text = temperature
    }
}