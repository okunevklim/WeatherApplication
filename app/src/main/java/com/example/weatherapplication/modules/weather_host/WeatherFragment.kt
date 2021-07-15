package com.example.weatherapplication.modules.weather_host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapplication.R
import com.example.weatherapplication.modules.weather_host.adapters.CategoryAdapter
import com.example.weatherapplication.databinding.FragmentWeatherBinding
import com.example.weatherapplication.shared.utils.Constants
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sections = arrayOf(R.string.current_weather, R.string.this_week_weather)
        val city = arguments?.getString(Constants.ARGS_CITY)
        setupViewPager(city ?: return)
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2, true, false
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(sections[position])
        }.attach()
    }

    private fun setupViewPager(city: String) = with(binding.viewPager2) {
        offscreenPageLimit = 2
        isUserInputEnabled = false
        adapter = CategoryAdapter(requireActivity(), city)
    }
}