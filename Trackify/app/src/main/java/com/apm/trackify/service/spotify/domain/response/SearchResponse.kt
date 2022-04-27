package com.apm.trackify.service.spotify.domain.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.domain.Track

data class SearchResponse(
    val tracks: SearchTracksResponse
) {

    fun toTrackItems(): List<TrackItem> {
        return tracks.toTrackItems()
    }

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
}