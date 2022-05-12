package com.apm.trackify.provider.repository

import androidx.paging.PagingData
import com.apm.trackify.provider.service.spotify.data.Track
import kotlinx.coroutines.flow.Flow

interface SpotifyRepository {

    fun getPlaylistTracks(playlistId: String): Flow<PagingData<Track>>
}