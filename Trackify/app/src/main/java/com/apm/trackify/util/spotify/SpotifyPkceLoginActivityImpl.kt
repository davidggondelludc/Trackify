package com.apm.trackify.util.spotify

import android.app.Activity
import android.content.Intent
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.pkce.AbstractSpotifyPkceLoginActivity
import com.apm.trackify.BuildConfig
import com.apm.trackify.TrackifyApplication
import com.apm.trackify.util.extension.toast

internal var pkceClassBackTo: Class<out Activity>? = null

class SpotifyPkceLoginActivityImpl : AbstractSpotifyPkceLoginActivity() {
    override val clientId = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE
    override val scopes = SpotifyScope.values().toList()

    override fun onSuccess(api: SpotifyClientApi) {
        val model = (application as TrackifyApplication).model
        model.credentialStore.setSpotifyApi(api)
        // TODO: Back To Activity
       /* val classBackTo = pkceClassBackTo ?: ActionHomeActivity::class.java
        pkceClassBackTo = null
        toast("Authentication via PKCE has completed. Launching ${classBackTo.simpleName}..")
        startActivity(Intent(this, classBackTo))*/
    }

    override fun onFailure(exception: Exception) {
        exception.printStackTrace()
        pkceClassBackTo = null
        toast("Auth failed: ${exception.message}")
    }
}