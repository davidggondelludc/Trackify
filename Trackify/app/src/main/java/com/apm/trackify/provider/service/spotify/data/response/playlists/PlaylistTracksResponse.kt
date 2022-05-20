package com.apm.trackify.provider.service.spotify.data.response.playlists

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.data.Track

data class PlaylistTracksResponse(
    val items: List<TrackResponse>,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
) {

    fun toTrackItems(): List<TrackItem> {
        val tracks = mutableListOf<TrackItem>()
        items.forEach {
            tracks.add(it.toTrackItem())
        }
        return tracks
    }

    data class TrackResponse(
        val track: Track
    ) {

        fun toTrackItem(): TrackItem =
            track.toTrackItem()
    }
}