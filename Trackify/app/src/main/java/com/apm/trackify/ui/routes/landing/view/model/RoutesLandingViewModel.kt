package com.apm.trackify.ui.routes.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist

class RoutesLandingViewModel : ViewModel() {

    val playlists = MutableLiveData<List<Playlist>>()

    init {
        playlists.value = MockProvider.playlists
    }
}