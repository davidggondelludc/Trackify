package com.apm.trackify.ui.playlists.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Track
import com.apm.trackify.util.extension.isInBounds
import com.apm.trackify.util.extension.swap

class PlaylistCreateViewModel: ViewModel() {

    private val tracks = MutableLiveData<List<Track>>()
    private val dataset: MutableList<Track> = MockProvider.tracks.toMutableList()

    init {
        tracks.value = dataset.toList() // Weird logic
    }

    fun getTracks(): LiveData<List<Track>> = tracks

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