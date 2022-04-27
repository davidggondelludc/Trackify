package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.SpotifyApi
import com.apm.trackify.ui.main.MainApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(playlistItem: PlaylistItem) : ViewModel() {

    val playlist = MutableLiveData<PlaylistItem>()
    val tracks = MutableLiveData<List<TrackItem>>()
    val errorMessage = MutableLiveData<String>()

    private var job: Job? = null

    init {
        this.playlist.value = playlistItem

        val retrofit =
            Retrofit.Builder().baseUrl("https://api.spotify.com/v1/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(SpotifyApi::class.java)
                .getPlaylistTracks(
                    playlistItem.id,
                    "Bearer ${MainApplication.TOKEN}"
                )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tracks.value = response.body()?.toTrackItems()
                } else {
                    errorMessage.value = "Error while loading tracks."
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}