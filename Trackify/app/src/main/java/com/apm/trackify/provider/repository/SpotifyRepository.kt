package com.apm.trackify.provider.repository

import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.enum.Duration

interface SpotifyRepository {

    suspend fun generateTracklist(duration: Duration): List<TrackItem>

    suspend fun createPlaylist(name: String, tracklist: List<TrackItem>)

    suspend fun searchTracks(query: String): List<TrackItem>

    suspend fun getMePlaylists(): List<PlaylistItem>

    suspend fun getPlaylistTracks(playlistId: String): List<TrackItem>
}