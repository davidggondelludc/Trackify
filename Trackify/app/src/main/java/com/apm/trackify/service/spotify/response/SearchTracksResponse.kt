package com.apm.trackify.service.spotify.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.domain.Track

data class SearchTracksResponse(
    val items: List<Track>,
    val total: Int
) {

    fun toTrackItems(): List<TrackItem> {
        val tracks = mutableListOf<TrackItem>()
        items.forEach {
            tracks.add(it.toTrackItem())
        }
        return tracks
    }
}