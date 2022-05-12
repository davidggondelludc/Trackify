package com.apm.trackify.provider.service.media

import android.media.MediaPlayer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaServiceLifecycle @Inject constructor() : DefaultLifecycleObserver {

    private var mediaPlayer: MediaPlayer? = null

    fun play(url: String) {
        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(url)
        mediaPlayer?.prepareAsync()
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }
        mediaPlayer?.setOnCompletionListener {
            stop()
        }
    }

    fun stop() {
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        stop()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        stop()
    }
}