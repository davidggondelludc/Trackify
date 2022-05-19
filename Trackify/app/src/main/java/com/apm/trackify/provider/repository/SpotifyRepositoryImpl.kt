package com.apm.trackify.provider.repository

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.SpotifyApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(
    private val spotifyApi: SpotifyApi
) : SpotifyRepository {

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