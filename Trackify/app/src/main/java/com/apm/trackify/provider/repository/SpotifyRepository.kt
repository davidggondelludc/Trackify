package com.apm.trackify.provider.repository

import com.apm.trackify.provider.model.domain.TrackItem

interface SpotifyRepository {

    suspend fun getPlaylistTracks(playlistId: String): List<TrackItem>
}