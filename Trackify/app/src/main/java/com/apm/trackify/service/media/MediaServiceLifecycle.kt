package com.apm.trackify.service.media

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
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}