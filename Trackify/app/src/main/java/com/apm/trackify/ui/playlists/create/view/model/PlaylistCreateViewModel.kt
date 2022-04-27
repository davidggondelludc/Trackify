package com.apm.trackify.ui.playlists.create.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.util.extension.isInBounds
import com.apm.trackify.util.extension.swap

/**
 * Weird logic: dataset.toList()
 *
 * If you pass the same list to the adapter it does not even call the DiffUtil. The library
 * assumes you are using Room or any other ORM which offers a new async list every time it
 * gets updated, so just calling submitList on it will work, and for sloppy developers, it
 * prevents doing the calculations twice if the same list is called.
 */
class PlaylistCreateViewModel : ViewModel() {

    val playlist = MutableLiveData<PlaylistItem>()
    val tracks = MutableLiveData<List<TrackItem>>()

    private val dataset: MutableList<TrackItem> = MockProvider.tracks.toMutableList()

    init {
        playlist.value = MockProvider.playlist
        tracks.value = dataset.toList() // Weird logic
    }

    fun move(from: Int, to: Int) {
        dataset.swap(from, to)
        tracks.value = dataset.toList() // Weird logic
    }

    fun remove(position: Int) {
        if (dataset.isInBounds(position)) {
            dataset.removeAt(position)
            tracks.value = dataset.toList() // Weird logic
        }
    }
}