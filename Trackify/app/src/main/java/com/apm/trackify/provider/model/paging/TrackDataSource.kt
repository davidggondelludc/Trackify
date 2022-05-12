package com.apm.trackify.provider.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.Track

class TrackDataSource(
    private val playlistId: String,
    private val spotifyApi: SpotifyApi
) : PagingSource<Int, Track>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val offset = params.key ?: 0
            val response = spotifyApi.getPlaylistTracks(playlistId, params.loadSize, offset)
            val page = response.body()
            val data = page?.items.orEmpty().map { it.track }
            val nextKey = if (page?.next == null) null else offset + params.loadSize

            LoadResult.Page(
                data,
                null, // Only paging forward.
                nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(state.config.pageSize)
        }
}