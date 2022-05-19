package com.apm.trackify.provider.service.spotify

import com.apm.trackify.provider.service.spotify.data.response.PlaylistsResponse
import com.apm.trackify.provider.service.spotify.data.response.SearchResponse
import com.apm.trackify.provider.service.spotify.data.response.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {

    @GET("me/playlists")
    suspend fun getMePlaylists(): Response<PlaylistsResponse>

    @GET("playlists/{playlist_id}/tracks")
    suspend fun getPlaylistTracks(
        @Path("playlist_id") playlistId: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Result<TracksResponse>

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String
    ): Response<SearchResponse>
}