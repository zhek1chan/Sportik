package com.example.sportik.presentation.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sportik.R
import com.example.sportik.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var changedTheme: String = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        changedTheme = sharedPref.getString(getString(R.string.changed_theme), changedTheme).toString()
        if ((Configuration.UI_MODE_NIGHT_NO == resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) && (changedTheme == "black")){
            changeTheme()
        } else if ((Configuration.UI_MODE_NIGHT_YES == resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) && (changedTheme == "white")){
            changeTheme()
        }

        binding.navView.setupWithNavController(navController)
    }

    private fun changeTheme() {
        val darkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkModeOn = darkMode == Configuration.UI_MODE_NIGHT_YES
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}