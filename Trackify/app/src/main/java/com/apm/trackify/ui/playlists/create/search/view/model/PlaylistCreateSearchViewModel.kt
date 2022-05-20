package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.response.search.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlaylistCreateSearchViewModel @Inject constructor(private val spotifyApi: SpotifyApi) :
    ViewModel() {

    private val response = MutableLiveData<Response<SearchResponse>>()

    fun getResponse(): LiveData<Response<SearchResponse>> = response

    fun searchTracks(query: String) {
        viewModelScope.launch {
            response.value = spotifyApi.search(query, "track")
        }
    }
}