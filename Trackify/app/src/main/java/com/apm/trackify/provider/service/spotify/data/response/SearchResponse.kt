package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.data.Track

data class SearchResponse(
    val tracks: SearchTracksResponse
) {

    fun toTrackItems(): List<TrackItem> =
        tracks.toTrackItems()

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