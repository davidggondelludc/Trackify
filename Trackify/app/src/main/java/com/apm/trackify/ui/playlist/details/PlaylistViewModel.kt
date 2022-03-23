package com.apm.trackify.ui.playlist.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.extensions.swap
import com.apm.trackify.ui.playlist.details.model.Track
import com.apm.trackify.ui.playlist.model.Playlist

class PlaylistViewModel : ViewModel() {

    private val playlist = MutableLiveData<Playlist>()
    private val tracks = MutableLiveData<List<Track>>()
    private val dataset: MutableList<Track> = PlaylistProvider.tracks.toMutableList()

    init {
        playlist.value = PlaylistProvider.playlist
        deepCopy()
    }

    fun getPlaylist(): LiveData<Playlist> = playlist

    fun getTracks(): LiveData<List<Track>> = tracks

    fun move(from: Int, to: Int) {
        dataset.swap(from, to)
        deepCopy()
    }

    fun remove(position: Int) {
        dataset.removeAt(position)
        deepCopy()
    }

    /*
     * TODO: Weird logic
     *
     * If you pass the same list to the adapter it does not even call the DiffUtil. The library
     * assumes you are using Room or any other ORM which offers a new async list every time it
     * gets updated, so just calling submitList on it will work, and for sloppy developers, it
     * prevents doing the calculations twice if the same list is called.
     */
    private fun deepCopy() {
        tracks.value = dataset.map { it.copy() }
    }
}