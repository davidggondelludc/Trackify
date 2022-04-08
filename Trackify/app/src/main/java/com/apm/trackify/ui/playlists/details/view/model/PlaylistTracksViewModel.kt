package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(playlist: Playlist) : ViewModel() {

    private val playlist = MutableLiveData<Playlist>()
    private val tracks = MutableLiveData<List<Track>>()

    init {
        this.playlist.value = playlist
        tracks.value = MockProvider.tracks.toMutableList()
    }

    fun getPlaylist(): LiveData<Playlist> = playlist

    fun getTracks(): LiveData<List<Track>> = tracks
}