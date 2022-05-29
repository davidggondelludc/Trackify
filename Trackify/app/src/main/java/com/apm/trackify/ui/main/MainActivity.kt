package com.apm.trackify.ui.main

import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var netCon: NetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.playlists_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.routes_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.user_fragment -> binding.bottomNavigation.visibility = View.VISIBLE
                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }

        FirebaseApp.initializeApp(this)

        binding.bottomNavigation.setupWithNavController(navController)

        checkNetworkConnection()

    }

    override fun onSupportNavigateUp(): Boolean {
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        return navController.navigateUp(appBarConfiguration)
    }

    private fun checkNetworkConnection() {
        netCon = NetworkConnection(application)

        val title = SpannableString("Internet Connection Alert")
        title.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, title.length, 0)

        val dialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("Please check your internet connection")
            .setIcon(R.drawable.ic_signal_wifi_bad).setCancelable(false)
            .setPositiveButton("Wait", null)
            .setNegativeButton("Close") { _, _ -> finish() }
            .create()
        dialog.setCanceledOnTouchOutside(false)

        dialog.setOnShowListener {
            val b: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            b.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    this,
                    "Waiting for connection to be re-established.",
                    Toast.LENGTH_LONG
                ).show()
            })
        }

        netCon.observe(this) { isConnected ->
            if (isConnected) {
                print("INTERNET ON")
            } else {
                AlertDialog.Builder(this).setIcon(android.R.drawable.ic_delete)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please check your internet connection")
                    .setPositiveButton("Close") { _, _ -> finish() }.show()
            }
        }
    }
}