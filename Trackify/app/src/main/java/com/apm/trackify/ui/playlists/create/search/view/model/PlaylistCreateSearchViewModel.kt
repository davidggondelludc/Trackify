package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistCreateSearchViewModel @Inject constructor() : ViewModel() {

    private val tracks = MutableLiveData<List<Track>>()

    init {
        tracks.value = MockProvider.tracks.toMutableList()
    }

    fun getTracks(): LiveData<List<Track>> = tracks
}