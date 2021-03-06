package com.apm.trackify.provider.service.media

import android.media.MediaPlayer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaServiceLifecycle @Inject constructor() : DefaultLifecycleObserver {

    private var position: Int? = null
    private var mediaPlayer: MediaPlayer? = null

    fun play(url: String, pos: Int) {
        position = pos
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

        position = null
        mediaPlayer = null
    }

    fun stop(pos: Int) {
        if (position == pos) {
            stop()
        }
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