package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.service.spotify.domain.response.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlaylistCreateSearchViewModel @Inject constructor(private val spotifyService: SpotifyService) :
    ViewModel() {

    private val response = MutableLiveData<Response<SearchResponse>>()

    fun getResponse(): LiveData<Response<SearchResponse>> = response

    fun searchTracks(query: String) {
        viewModelScope.launch {
            response.value = spotifyService.search(query, "track")
        }
    }
}