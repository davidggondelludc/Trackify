package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.SpotifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(
    playlistItem: PlaylistItem,
    spotifyRepository: SpotifyRepository
) : ViewModel() {

    val error = MutableLiveData<Int>()
    val tracks = MutableLiveData<List<TrackItem>>()

    init {
        viewModelScope.launch {
            try {
                tracks.value = spotifyRepository.getPlaylistTracks(playlistItem.id)
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }
}