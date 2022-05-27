package com.apm.trackify.service.spotify

import com.apm.trackify.service.spotify.domain.Owner
import com.apm.trackify.service.spotify.domain.response.PlaylistsResponse
import com.apm.trackify.service.spotify.domain.response.SearchResponse
import com.apm.trackify.service.spotify.domain.response.TopTracksResponse
import com.apm.trackify.service.spotify.domain.response.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("me/top/tracks")
    suspend fun getMeTopTracks(): Response<TopTracksResponse>

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

    @GET("users/{user_id}")
    suspend fun getUserInfo(
        @Path("user_id") userId: String
    ) : Response<Owner>
}