package com.apm.trackify.provider.repository

import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.enum.Duration
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.Track
import com.apm.trackify.provider.service.spotify.data.request.PlaylistRequest
import com.apm.trackify.provider.service.spotify.data.request.PlaylistTracksRequest
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import javax.inject.Inject
import kotlin.math.abs

class SpotifyRepositoryImpl @Inject constructor(
    private val spotifyApi: SpotifyApi
) : SpotifyRepository {

    override suspend fun generateTracklist(duration: Duration): List<TrackItem> {
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

        return closest(
            tracklist.distinct().toMutableList(),
            artist.popularity,
            duration.tracks
        ).run {
            shuffle()
            map { it.toTrackItem() }
        }
    }

    override suspend fun createPlaylist(name: String, tracklist: List<TrackItem>) {
        val user = spotifyApi.getMeUser().getOrThrow()
        val playlist = if (name == "")
            spotifyApi.createPlaylist(user.id, PlaylistRequest("Playlist by trackify")).getOrThrow()
        else {
            spotifyApi.createPlaylist(user.id, PlaylistRequest("$name by trackify")).getOrThrow()
        }

        for (tracks in tracklist.chunked(100)) {
            spotifyApi.addTracksToPlaylist(
                playlist.id,
                PlaylistTracksRequest(tracks.map { it.uri })
            )
        }
    }

    override suspend fun searchTracks(query: String): List<TrackItem> {
        return spotifyApi.search(query).getOrThrow().toTrackItems()
    }

    override suspend fun getMePlaylists(): List<PlaylistItem> {
        return spotifyApi.getMePlaylists().getOrThrow().toPlaylistItems()
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

    private fun closest(
        tracklist: MutableList<Track>,
        popularity: Int,
        total: Int
    ): MutableList<Track> {
        val finalTrackList = mutableListOf<Track>()
        val finalTotal = if (tracklist.size > total) total else tracklist.size
        val comparator = Comparator { prev: Track, next: Track ->
            return@Comparator abs(popularity - prev.popularity) - abs(popularity - next.popularity)
        }

        while (finalTrackList.size != finalTotal) {
            val track = tracklist.run {
                sortWith(comparator)
                removeFirst()
                first()
            }
            finalTrackList.add(track)
        }

        return finalTrackList
    }
}