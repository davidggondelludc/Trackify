package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist

class RoutesCreateViewModel : ViewModel() {

    private val playlists = MutableLiveData<List<Playlist>>()

    init {
        playlists.value = MockProvider.playlists
    }

    fun getPlaylists(): LiveData<List<Playlist>> = playlists
}