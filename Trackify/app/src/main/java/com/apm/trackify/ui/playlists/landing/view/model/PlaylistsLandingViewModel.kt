package com.apm.trackify.ui.playlists.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.service.SpotifyApi
import com.apm.trackify.ui.main.MainApplication
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistsLandingViewModel : ViewModel() {

    val playlists = MutableLiveData<List<PlaylistItem>>()
    val errorMessage = MutableLiveData<String>()

    private var job: Job? = null

    init {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.spotify.com/v1/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response =
                retrofit.create(SpotifyApi::class.java)
                    .getMePlaylists("Bearer ${MainApplication.TOKEN}")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    playlists.value = response.body()?.toPlaylistItems()
                } else {
                    errorMessage.value = "Error while loading playlists."
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}