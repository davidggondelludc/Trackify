package com.apm.trackify.service.spotify.domain.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.domain.Track

data class TracksResponse(
    val items: List<TrackResponse>,
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

        fun toTrackItem(): TrackItem = track.toTrackItem()
    }
}