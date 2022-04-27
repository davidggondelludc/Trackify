package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.SpotifyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(
    playlistItem: PlaylistItem,
    spotifyService: SpotifyService
) : ViewModel() {

    val playlist = MutableLiveData<PlaylistItem>()
    val tracks = MutableLiveData<List<TrackItem>>()
    val errorMessage = MutableLiveData<String>()

    init {
        this.playlist.value = playlistItem

        viewModelScope.launch {
            val response = spotifyService.getPlaylistTracks(playlistItem.id)

            if (response.isSuccessful) {
                tracks.value = response.body()?.toTrackItems()
            } else {
                errorMessage.value = "Error while loading tracks."
            }
        }
    }
}