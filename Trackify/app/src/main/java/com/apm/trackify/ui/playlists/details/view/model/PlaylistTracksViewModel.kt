package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(playlist: Playlist) : ViewModel() {

    val playlist = MutableLiveData<Playlist>()
    val tracks = MutableLiveData<List<Track>>()

    init {
        this.playlist.value = playlist
        tracks.value = MockProvider.tracks.toMutableList()
    }
}