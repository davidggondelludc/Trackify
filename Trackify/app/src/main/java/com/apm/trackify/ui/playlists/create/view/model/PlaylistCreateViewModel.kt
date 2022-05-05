package com.apm.trackify.ui.playlists.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.util.extension.isInBounds
import com.apm.trackify.util.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Weird logic: dataset.toList()
 *
 * If you pass the same list to the adapter it does not even call the DiffUtil. The library
 * assumes you are using Room or any other ORM which offers a new async list every time it
 * gets updated, so just calling submitList on it will work, and for sloppy developers, it
 * prevents doing the calculations twice if the same list is called.
 */
@HiltViewModel
class PlaylistCreateViewModel @Inject constructor(spotifyService: SpotifyService) : ViewModel() {

    val playlist = MutableLiveData<PlaylistItem>()

    private val tracks = MutableLiveData<List<TrackItem>>()
    fun getTracks(): LiveData<List<TrackItem>> = tracks
    private val dataset = mutableListOf<TrackItem>()

    init {
        playlist.value = MockProvider.playlist

        viewModelScope.launch {
            val response = spotifyService.getMeTopTracks()

            if (response.isSuccessful) {
                response.body()?.toTrackItems()?.let { dataset.addAll(it) }
                tracks.value = dataset.toList()
            }
        }
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