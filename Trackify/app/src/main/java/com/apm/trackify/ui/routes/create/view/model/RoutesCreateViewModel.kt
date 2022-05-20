package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.response.me.MePlaylistsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoutesCreateViewModel @Inject constructor(spotifyApi: SpotifyApi) : ViewModel() {

    private val response = MutableLiveData<Response<MePlaylistsResponse>>()

    fun getResponse(): LiveData<Response<MePlaylistsResponse>> = response

    init {
        viewModelScope.launch {
            response.value = spotifyApi.getMePlaylists()
        }
    }
}