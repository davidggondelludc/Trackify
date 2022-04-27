package com.apm.trackify.model.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface SpotifyApi {
    @GET
    suspend fun getPlaylists(
        @Url url: String,
        @Header("Authorization") token: String
    ): Response<PlaylistsResponse>

    @GET
    suspend fun getTracks(
        @Url url: String,
        @Header("Authorization") token: String
    ): Response<TracksResponse>

    @GET
    suspend fun findTracks(
        @Url url: String,
        @Header("Authorization") token: String
    ): Response<SearchResponse>
}