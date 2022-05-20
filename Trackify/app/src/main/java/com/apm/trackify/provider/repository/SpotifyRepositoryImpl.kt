package com.apm.trackify.provider.repository

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.Track
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(
    private val spotifyApi: SpotifyApi
) : SpotifyRepository {

    override suspend fun generateTracklist(): List<TrackItem> {
        val tracklist = mutableListOf<Track>()

        val artist = spotifyApi.getMeTopArtists().getOrThrow().items.random()
        val relatedArtists =
            spotifyApi.getArtistRelatedArtists(artist.id).getOrThrow().artists.toMutableList()
        relatedArtists.add(artist)

        for (relatedArtist in relatedArtists.reversed()) {
            val topTracks = spotifyApi.getArtistTopTracks(relatedArtist.id).getOrThrow().tracks
            for (track in topTracks.reversed()) {
                tracklist.add(track)
            }
        }

        return tracklist.run {
            sortBy { it.popularity }
            map { it.toTrackItem() }
        }
    }

    override suspend fun getPlaylistTracks(playlistId: String): List<TrackItem> {
        val tracks = mutableListOf<TrackItem>()
        coroutineScope {
            val limit = 100
            var offset = 0

            do {
                val response = spotifyApi.getPlaylistTracks(playlistId, limit, offset).getOrThrow()
                tracks.addAll(response.toTrackItems())
                offset += limit
            } while (response.next != null && isActive)
        }

        return tracks
    }

    
}