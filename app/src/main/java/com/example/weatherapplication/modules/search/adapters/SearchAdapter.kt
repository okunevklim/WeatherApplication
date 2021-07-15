package com.example.weatherapplication.modules.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.LayoutItemCityBinding

class SearchAdapter(private val onCitySelectedClosure: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cities = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityViewHolder(
            LayoutItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CityViewHolder).bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun updateCities(newCities: List<String>) {
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }

    inner class CityViewHolder(private val binding: LayoutItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: String) = with(binding.root) {
            text = city
            setOnClickListener { onCitySelectedClosure(city) }
        }
    }
}