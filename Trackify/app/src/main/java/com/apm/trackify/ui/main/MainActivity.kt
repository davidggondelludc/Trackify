package com.apm.trackify.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition and must be called before super.onCreate()
        installSplashScreen()

        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when (destination.id) {
                R.id.playlists_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.routes_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.user_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun firebaseInit() {

        //val serviceAccount = FileInputStream("path/to/serviceAccountKey.json")

        //val options = FirebaseOptions.Builder().
        //val options: FirebaseOptions = FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build()

        //FirebaseApp.initializeApp(options)

    }
}