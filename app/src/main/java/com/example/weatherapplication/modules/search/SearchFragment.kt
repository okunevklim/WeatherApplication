package com.example.weatherapplication.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentSearchBinding
import com.example.weatherapplication.modules.search.adapters.SearchAdapter
import com.example.weatherapplication.modules.search.utils.JSONUtils
import com.example.weatherapplication.shared.utils.Constants
import com.example.weatherapplication.modules.search.utils.hideKeyboard
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var inputDisposable: Disposable

    private lateinit var cities: ArrayList<String>

    private val adapter = SearchAdapter { selectCity(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleProgressBar(false)
        cities = JSONUtils.loadCities(requireContext())
        initRecycler()
        initSearching()
    }

    private fun initRecycler() {
        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecycler.adapter = adapter
    }

    private fun toggleProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun initSearching() {
        toggleProgressBar(true)
        inputDisposable = binding.searchField.textChanges()
            .debounce(TIMEOUT, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                toggleProgressBar(false)
                performSearch(it)
            }
    }

    private fun performSearch(city: String) {
        binding.emptySearchResult.isVisible = false
        val searchedCities = cities.filter { s -> city.isNotEmpty() && s.contains(city) }
        adapter.updateCities(searchedCities)
        binding.searchRecycler.isVisible = searchedCities.isNotEmpty()
    }

    private fun selectCity(city: String) {
        binding.searchField.hideKeyboard()
        navigateToWeatherFragment(city)
    }

    private fun navigateToWeatherFragment(city: String) {
        val navController = requireActivity().findNavController(R.id.navHostFragment)
        navController.navigate(R.id.action_see_weather, bundleOf(Constants.ARGS_CITY to city))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!inputDisposable.isDisposed) {
            inputDisposable.dispose()
        }
    }

    companion object {
        const val ZERO = 0
        const val TIMEOUT = 300L
    }
}