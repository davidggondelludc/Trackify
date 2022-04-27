package com.apm.trackify.ui.routes.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.PlaylistItem

class RoutesLandingViewModel : ViewModel() {

    val playlists = MutableLiveData<List<PlaylistItem>>()

    init {
        playlists.value = MockProvider.playlists
    }
}