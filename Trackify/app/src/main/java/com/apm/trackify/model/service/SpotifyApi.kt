package com.apm.trackify.model.service

import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.prueba.PlaylistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface SpotifyApi {
    @GET
    suspend fun getPlaylists(@Url url:String, @Header("Authorization") token: String ):Response<PlaylistsResponse>
}