package com.apm.trackify.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.ui.playlists.model.Playlist
import com.apm.trackify.utils.MockProvider

class RoutesLandingViewModel : ViewModel() {

    private val playlists = MutableLiveData<List<Playlist>>()

    init {
        playlists.value = MockProvider.playlists
    }

    fun getPlaylists(): LiveData<List<Playlist>> = playlists
}