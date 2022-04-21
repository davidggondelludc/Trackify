package com.apm.trackify

import android.content.Context
import android.app.Application
import com.apm.trackify.util.spotify.data.Model

class TrackifyApplication : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}