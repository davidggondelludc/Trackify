package com.apm.trackify.ui.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.apm.trackify.databinding.LoginActivityBinding
import com.apm.trackify.ui.login.view.model.LoginActivityViewModel
import com.apm.trackify.ui.main.MainActivity
import com.apm.trackify.ui.main.MainApplication
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    companion object {
        const val CLIENT_ID = "99a7a1290b394fc8b56c256099456c3c"
        const val REDIRECT_URI = "trackify://authcallback"
        const val AUTH_TOKEN_REQUEST_CODE = 0x10
    }

    private var isReady = false
    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition and must be called before super.onCreate()
        installSplashScreen()

        super.onCreate(savedInstanceState)

        val binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
                .setShowDialog(false)
                .setScopes(
                    arrayOf(
                        "user-top-read",
                        "user-read-email",
                        "user-read-private",
                        "playlist-read-private",
                        "playlist-read-collaborative",
                        "playlist-modify-public",
                        "playlist-modify-private"
                    )
                )
                .build()
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)

        checkLocationPermission()

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //for solving my login problems
        /*
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
         */

        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            if (response.type == AuthorizationResponse.Type.TOKEN) {
                MainApplication.TOKEN = response.accessToken

                viewModel.checkUserCreated({
                    val mainActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainActivityIntent)
                    finish()
                }, {
                    val loginActivityIntent = Intent(this, LoginActivity::class.java)
                    startActivity(loginActivityIntent)
                    finish()
                })
            }
        }
    }

    override fun onBackPressed() {
        finish()
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