package com.apm.trackify.ui.playlists.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.service.spotify.SpotifyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistsLandingViewModel @Inject constructor(spotifyService: SpotifyService) : ViewModel() {

    val playlists = MutableLiveData<List<PlaylistItem>>()
    val errorMessage = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val response = spotifyService.getMePlaylists()

            if (response.isSuccessful) {
                playlists.postValue(response.body()?.toPlaylistItems())
            } else {
                errorMessage.value = "Error while loading playlists."
            }
        }
    }
}