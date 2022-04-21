package com.apm.trackify.util.spotify.data

import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.apm.trackify.BuildConfig

import com.apm.trackify.TrackifyApplication

object Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = TrackifyApplication.context
        )
    }
}