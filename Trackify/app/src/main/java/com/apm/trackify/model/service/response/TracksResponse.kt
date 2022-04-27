package com.apm.trackify.model.service.response

import com.apm.trackify.model.domain.TrackItem

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
}