package com.apm.trackify.ui.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.apm.trackify.databinding.LoginActivityBinding
import com.apm.trackify.ui.main.MainActivity
import com.apm.trackify.ui.main.MainApplication
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    companion object {
        const val CLIENT_ID = "99a7a1290b394fc8b56c256099456c3c"
        const val REDIRECT_URI = "trackify://authcallback"
        const val AUTH_TOKEN_REQUEST_CODE = 0x10
    }

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition and must be called before super.onCreate()
        installSplashScreen()

        super.onCreate(savedInstanceState)

        val binding = LoginActivityBinding.inflate(layoutInflater)
        binding.login.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready
                    return if (isReady) {
                        binding.login.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )
        setContentView(binding.root)

        val request =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
                .setShowDialog(false)
                .setScopes(arrayOf("playlist-read-private"))
                .build()
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
        binding.login.setOnClickListener {
            AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
        }

        checkLocationPermission()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //for solving my login problems

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        /*
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            if (response.type == AuthorizationResponse.Type.TOKEN) {
                val intent = Intent(this, MainActivity::class.java)
                MainApplication.TOKEN = response.accessToken
                startActivity(intent)
                finish()
            }
            isReady = true
        }
        */

    }

    private fun checkLocationPermission() {

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1001
            )
        }
    }


}