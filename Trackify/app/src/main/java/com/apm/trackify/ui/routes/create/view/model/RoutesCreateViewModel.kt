package com.apm.trackify.ui.routes.create.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.service.spotify.SpotifyAPI
import com.apm.trackify.service.spotify.model.mapper.PlaylistMapper
import com.apm.trackify.ui.main.MainApplication
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoutesCreateViewModel : ViewModel() {

    val playlists = MutableLiveData<List<Playlist>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    init {
        val tk = "Bearer ${MainApplication.TOKEN}"
        val rt = Retrofit.Builder().baseUrl("https://api.spotify.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        job = CoroutineScope(Dispatchers.IO).launch {
            val call =
                rt.create(SpotifyAPI::class.java).getPlaylists("v1/me/playlists", tk)

            withContext(Dispatchers.Main) {
                if (call.isSuccessful) {
                    val res = call.body()
                    playlists.value = PlaylistMapper.toPlaylist(res!!)
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