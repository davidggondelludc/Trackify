package com.apm.trackify.ui.playlists.landing.view.model

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.service.PlaylistsMapper
import com.apm.trackify.model.service.SpotifyApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistsLandingViewModel : ViewModel() {

    val playlists = MutableLiveData<List<Playlist>>()
    val errorMessage = MutableLiveData<String>()
    var job:Job? = null

    init {
        val tk = "Bearer tk"
        val rt = Retrofit.Builder().baseUrl("https://api.spotify.com/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val call =
                rt.create(SpotifyApi::class.java).getPlaylists("v1/me/playlists", tk)

            withContext(Dispatchers.Main) {
                if (call.isSuccessful) {
                    val res = call.body()
                    playlists.value = PlaylistsMapper().mapPlaylists(res!!)
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