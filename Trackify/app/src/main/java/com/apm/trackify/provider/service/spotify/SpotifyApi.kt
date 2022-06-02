package com.apm.trackify.provider.service.spotify

import com.apm.trackify.provider.service.spotify.data.Owner
import com.apm.trackify.provider.service.spotify.data.Playlist
import com.apm.trackify.provider.service.spotify.data.User
import com.apm.trackify.provider.service.spotify.data.request.PlaylistRequest
import com.apm.trackify.provider.service.spotify.data.request.PlaylistTracksRequest
import com.apm.trackify.provider.service.spotify.data.response.artists.ArtistRelatedArtistsResponse
import com.apm.trackify.provider.service.spotify.data.response.artists.ArtistTopTracksResponse
import com.apm.trackify.provider.service.spotify.data.response.me.MePlaylistsResponse
import com.apm.trackify.provider.service.spotify.data.response.me.MeTopArtistsResponse
import com.apm.trackify.provider.service.spotify.data.response.playlists.PlaylistTracksResponse
import com.apm.trackify.provider.service.spotify.data.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.*

interface SpotifyApi {

    @GET("me")
    suspend fun getMeUser(): Result<User>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): Result<User>

    @GET("me/playlists")
    suspend fun getMePlaylists(): Result<MePlaylistsResponse>

    @GET("me/top/artists")
    suspend fun getMeTopArtists(): Result<MeTopArtistsResponse>

    @POST("users/{id}/playlists")
    suspend fun createPlaylist(
        @Path("id") id: String,
        @Body playlist: PlaylistRequest
    ): Result<Playlist>

    @GET("users/{user_id}")
    suspend fun getUserInfo(
        @Path("user_id") userId: String
    ): Response<Owner>

    @GET("artists/{id}/related-artists")
    suspend fun getArtistRelatedArtists(
        @Path("id") id: String
    ): Result<ArtistRelatedArtistsResponse>

    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(
        @Path("id") id: String,
        @Query("market") market: String = "ES"
    ): Result<ArtistTopTracksResponse>

    @GET("playlists/{playlist_id}")
    suspend fun getPlaylistById(
        @Path("playlist_id") playlistId: String
    ): Response<Playlist>

    @GET("playlists/{id}/tracks")
    suspend fun getPlaylistTracks(
        @Path("id") id: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Result<PlaylistTracksResponse>

    @POST("playlists/{id}/tracks")
    suspend fun addTracksToPlaylist(
        @Path("id") id: String,
        @Body uris: PlaylistTracksRequest
    )

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String = "track"
    ): Result<SearchResponse>
}