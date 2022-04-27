package com.apm.trackify.model.service

import com.apm.trackify.model.service.response.PlaylistsResponse
import com.apm.trackify.model.service.response.SearchResponse
import com.apm.trackify.model.service.response.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {

    @GET("me/playlists")
    suspend fun getMePlaylists(
        @Header("Authorization") token: String
    ): Response<PlaylistsResponse>

    @GET("playlists/{playlist_item}/tracks")
    suspend fun getPlaylistTracks(
        @Path("playlist_item") playlistItem: String,
        @Header("Authorization") token: String
    ): Response<TracksResponse>

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String,
        @Header("Authorization") token: String
    ): Response<SearchResponse>
}