package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.PlaylistItem

class RoutesCreateViewModel : ViewModel() {

    private val playlists = MutableLiveData<List<PlaylistItem>>()

    init {
        playlists.value = MockProvider.playlists
    }

    fun getPlaylists(): LiveData<List<PlaylistItem>> = playlists
}