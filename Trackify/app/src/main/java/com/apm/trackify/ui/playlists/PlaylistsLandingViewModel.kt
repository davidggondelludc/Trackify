package com.apm.trackify.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist

class PlaylistsLandingViewModel : ViewModel() {

    private val playlists = MutableLiveData<List<Playlist>>()

    init {
        playlists.value = MockProvider.playlists
    }

    fun getPlaylists(): LiveData<List<Playlist>> = playlists
}