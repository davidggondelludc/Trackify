package com.apm.trackify.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.profile_fragment
            )
        )

        setContentView(binding.root)

        setupActionBar()
//        setupToolbar(binding.toolbar)
        setupBottomNavigationView(binding.bottomNavigation)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun setupActionBar() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationView(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setupWithNavController(navController)
    }
}