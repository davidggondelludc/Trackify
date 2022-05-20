package com.apm.trackify.provider.service.spotify

import com.apm.trackify.provider.service.spotify.data.response.artists.ArtistRelatedArtistsResponse
import com.apm.trackify.provider.service.spotify.data.response.artists.ArtistTopTracksResponse
import com.apm.trackify.provider.service.spotify.data.response.me.MePlaylistsResponse
import com.apm.trackify.provider.service.spotify.data.response.me.MeTopArtistsResponse
import com.apm.trackify.provider.service.spotify.data.response.playlists.PlaylistTracksResponse
import com.apm.trackify.provider.service.spotify.data.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {

    @GET("me/playlists")
    suspend fun getMePlaylists(): Response<MePlaylistsResponse>

    @GET("me/top/artists")
    suspend fun getMeTopArtists(): Result<MeTopArtistsResponse>

    @GET("artists/{id}/related-artists")
    suspend fun getArtistRelatedArtists(
        @Path("id") id: String
    ): Result<ArtistRelatedArtistsResponse>

    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(
        @Path("id") id: String,
        @Query("market") market: String = "ES"
    ): Result<ArtistTopTracksResponse>

    @GET("playlists/{id}/tracks")
    suspend fun getPlaylistTracks(
        @Path("id") id: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Result<PlaylistTracksResponse>

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String
    ): Response<SearchResponse>
}