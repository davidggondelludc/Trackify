package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.service.spotify.domain.response.PlaylistsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoutesCreateViewModel @Inject constructor(spotifyService: SpotifyService) : ViewModel() {

    private val response = MutableLiveData<Response<PlaylistsResponse>>()

    fun getResponse(): LiveData<Response<PlaylistsResponse>> = response

    init {
        viewModelScope.launch {
            response.value = spotifyService.getMePlaylists()
        }
    }
}