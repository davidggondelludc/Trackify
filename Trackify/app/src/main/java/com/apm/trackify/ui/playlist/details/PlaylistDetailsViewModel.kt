package com.apm.trackify.ui.playlist.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.extensions.isInBounds
import com.apm.trackify.extensions.swap
import com.apm.trackify.utils.MockProvider
import com.apm.trackify.ui.playlist.details.model.Track
import com.apm.trackify.ui.playlist.model.Playlist

/*
 * Weird logic: dataset.toList()
 *
 * If you pass the same list to the adapter it does not even call the DiffUtil. The library
 * assumes you are using Room or any other ORM which offers a new async list every time it
 * gets updated, so just calling submitList on it will work, and for sloppy developers, it
 * prevents doing the calculations twice if the same list is called
 */
class PlaylistDetailsViewModel : ViewModel() {

    private val playlist = MutableLiveData<Playlist>()
    private val tracks = MutableLiveData<List<Track>>()
    private val dataset: MutableList<Track> = MockProvider.tracks.toMutableList()

    init {
        playlist.value = MockProvider.playlist
        tracks.value = dataset.toList()
    }

    fun getPlaylist(): LiveData<Playlist> = playlist

    fun getTracks(): LiveData<List<Track>> = tracks

    fun move(from: Int, to: Int) {
        dataset.swap(from, to)
        tracks.value = dataset.toList()
    }

    fun remove(position: Int) {
        if (dataset.isInBounds(position)) {
            dataset.removeAt(position)
            tracks.value = dataset.toList()
        }
    }
}