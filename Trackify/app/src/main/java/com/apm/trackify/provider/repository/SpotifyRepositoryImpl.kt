package com.apm.trackify.provider.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.model.paging.TrackDataSource
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(private val spotifyApi: SpotifyApi) :
    SpotifyRepository {

    override fun getPlaylistTracks(playlistId: String): Flow<PagingData<Track>> =
        Pager(PagingConfig(10), 0) {
            TrackDataSource(playlistId, spotifyApi)
        }.flow
}