package com.example.weatherapplication.shared

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        navigateToSearchFragment()
    }

    private fun navigateToSearchFragment() {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController.setGraph(R.navigation.nav_graph)
        val navController = findNavController(R.id.navHostFragment)
        navController.navigate(R.id.searchFragment)
    }
}