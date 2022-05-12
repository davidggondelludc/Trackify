package com.apm.trackify.ui.playlists.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.spotify.SpotifyApi
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
class PlaylistCreateViewModel @Inject constructor(spotifyApi: SpotifyApi) : ViewModel() {

    val playlist = MutableLiveData<PlaylistItem>()

    private val tracks = MutableLiveData<List<UiModel.TrackItem>>()
    fun getTracks(): LiveData<List<UiModel.TrackItem>> = tracks
    private val dataset = mutableListOf<UiModel.TrackItem>()

    init {
        playlist.value = MockProvider.playlist

        viewModelScope.launch {
//            val response = spotifyService.getMeTopTracks()
//
//            if (response.isSuccessful) {
//                response.body()?.toTrackItems()?.let { dataset.addAll(it) }
//                tracks.value = dataset.toList()
//            }
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