package com.apm.trackify.ui.playlists.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        val tk = "Bearer BQAfBeZyt0RhoOk4_M8JGB1YN17Kvemp9z-lamZecx1oWQDxt6BugHLZMmoAN7oarzFVxoQjtd_u0jk4mtBAB-1Jr_jgJG-uqhm10smC5yqhXABd2DNWxFpEJizCWSuxT6Iz3S9ShbVM4NNH8wS8imU_tUVx1eiMTziXPkA5KHV5ubVCF_B5q4o"
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