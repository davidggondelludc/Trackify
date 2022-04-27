package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.service.spotify.domain.response.TracksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(
    playlistItem: PlaylistItem,
    spotifyService: SpotifyService
) : ViewModel() {

    val response = MutableLiveData<Response<TracksResponse>>()

    fun getResponse(): LiveData<Response<TracksResponse>> = response

    init {
        viewModelScope.launch {
            response.value = spotifyService.getPlaylistTracks(playlistItem.id)
        }
    }
}