package com.apm.trackify.model.service.response

import com.apm.trackify.model.domain.TrackItem

data class SearchResponse(
    val tracks: SearchTracksResponse
) {

    fun toTrackItems(): List<TrackItem> {
        return tracks.toTrackItems()
    }
}