package com.apm.trackify.service.spotify

import com.apm.trackify.service.spotify.domain.response.PlaylistsResponse
import com.apm.trackify.service.spotify.domain.response.SearchResponse
import com.apm.trackify.service.spotify.domain.response.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    companion object {
        const val BASE_URL: String = "https://api.spotify.com/v1/"
    }

    @GET("me/playlists")
    suspend fun getMePlaylists(): Response<PlaylistsResponse>

    @GET("playlists/{playlist_item}/tracks")
    suspend fun getPlaylistTracks(
        @Path("playlist_item") playlistItem: String
    ): Response<TracksResponse>

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String
    ): Response<SearchResponse>
}