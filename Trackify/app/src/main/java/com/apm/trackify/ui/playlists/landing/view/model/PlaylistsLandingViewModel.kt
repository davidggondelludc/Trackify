package com.apm.trackify.ui.playlists.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.repository.SpotifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PlaylistsLandingViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel() {

    val error = MutableLiveData<Int>()
    val playlists = MutableLiveData<List<PlaylistItem>>()

    init {
        getMePlaylists()
    }

    fun getMePlaylists() {
        viewModelScope.launch {
            try {
                playlists.value = spotifyRepository.getMePlaylists()
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }
}