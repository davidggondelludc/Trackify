package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Track

class PlaylistCreateSearchViewModel : ViewModel() {

    val tracks = MutableLiveData<List<Track>>()

    init {
        tracks.value = MockProvider.tracks.toMutableList()
    }
}