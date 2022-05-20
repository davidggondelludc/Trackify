package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.SpotifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistCreateSearchViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel() {

    val tracks = MutableLiveData<List<TrackItem>>()

    fun searchTracks(query: String) {
        viewModelScope.launch {
            tracks.value = spotifyRepository.searchTracks(query)
        }
    }
}