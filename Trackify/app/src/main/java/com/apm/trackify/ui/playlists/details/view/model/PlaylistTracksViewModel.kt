package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Track
import com.apm.trackify.model.service.SpotifyApi
import com.apm.trackify.model.service.TracksMapper
import com.apm.trackify.ui.main.MainApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(playlist: Playlist) : ViewModel() {

    val playlist = MutableLiveData<Playlist>()
    val tracks = MutableLiveData<List<Track>>()
    private val errorMessage = MutableLiveData<String>()
    private var job: Job? = null

    init {
        this.playlist.value = playlist
        // tracks.value = MockProvider.tracks.toMutableList()
        val tk = "Bearer ${MainApplication.TOKEN}"
        val rt = Retrofit.Builder().baseUrl("https://api.spotify.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val call = rt.create(SpotifyApi::class.java)
                .getTracks("v1/playlists/" + playlist.id + "/tracks", tk)

            withContext(Dispatchers.Main) {
                if (call.isSuccessful) {
                    val res = call.body()
                    tracks.value = TracksMapper().mapTracks(res!!)
                } else {
                    errorMessage.value = "Error while loading tracks."
                }
            }
        }

    }
}