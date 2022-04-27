package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.model.service.SpotifyApi
import com.apm.trackify.ui.main.MainApplication
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistCreateSearchViewModel : ViewModel() {

    val tracks = MutableLiveData<List<TrackItem>>()
    private val errorMessage = MutableLiveData<String>()
    private var job: Job? = null

    fun searchTracks(query: String) {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.spotify.com/v1/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(SpotifyApi::class.java)
                .search(query, "track", "Bearer ${MainApplication.TOKEN}")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tracks.value = response.body()?.toTrackItems()
                } else {
                    errorMessage.value = "Error while searching tracks."
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}