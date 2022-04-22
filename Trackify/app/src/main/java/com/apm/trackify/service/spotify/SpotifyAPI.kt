package com.apm.trackify.service.spotify

import com.apm.trackify.service.spotify.model.domain.PlaylistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface SpotifyAPI {
    @GET
    suspend fun getPlaylists(
        @Url url: String,
        @Header("Authorization") token: String
    ): Response<PlaylistsResponse>
}