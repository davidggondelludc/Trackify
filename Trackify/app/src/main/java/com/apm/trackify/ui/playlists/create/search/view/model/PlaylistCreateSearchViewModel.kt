package com.apm.trackify.ui.playlists.create.search.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Track
import com.apm.trackify.model.service.SearchMapper
import com.apm.trackify.model.service.SpotifyApi
import com.apm.trackify.ui.main.MainApplication
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistCreateSearchViewModel : ViewModel() {

    val tracks = MutableLiveData<List<Track>>()
    private val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    init {
        tracks.value = MockProvider.tracks
    }

    fun updateTracks(search: String) {
        val tk = "Bearer ${MainApplication.TOKEN}"
        val rt = Retrofit.Builder().baseUrl("https://api.spotify.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val call =
                rt.create(SpotifyApi::class.java)
                    .findTracks("v1/search?query=$search&type=track", tk)

            withContext(Dispatchers.Main) {
                if (call.isSuccessful) {
                    val res = call.body()
                    tracks.value = SearchMapper().mapSearch(res!!)
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