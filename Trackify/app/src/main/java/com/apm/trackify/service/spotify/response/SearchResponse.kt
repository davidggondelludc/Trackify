package com.apm.trackify.service.spotify.response

import com.apm.trackify.model.domain.TrackItem

data class SearchResponse(
    val tracks: SearchTracksResponse
) {

    fun toTrackItems(): List<TrackItem> {
        return tracks.toTrackItems()
    }
}