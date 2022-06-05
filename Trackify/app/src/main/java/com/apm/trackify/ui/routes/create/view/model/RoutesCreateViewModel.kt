package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.repository.SpotifyRepository
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RoutesCreateViewModel @Inject constructor(
    spotifyRepository: SpotifyRepository,
    spotifyApi: SpotifyApi
) : ViewModel() {

    val error = MutableLiveData<Int>()
    val playlists = MutableLiveData<List<PlaylistItem>>()
    val user = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            try {
                val publicPlaylists = spotifyRepository.getMePlaylists().filter { it.public }
                playlists.value = publicPlaylists
                user.value = spotifyApi.getMeUser().getOrNull()
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }
}