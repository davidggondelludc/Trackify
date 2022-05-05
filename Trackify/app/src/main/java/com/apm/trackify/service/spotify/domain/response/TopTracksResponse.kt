package com.apm.trackify.service.spotify.domain.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.domain.Track

data class TopTracksResponse(
    val items: List<Track>
) {
    
    fun toTrackItems(): List<TrackItem> {
        val tracks = mutableListOf<TrackItem>()
        items.forEach {
            tracks.add(it.toTrackItem())
        }
        return tracks
    }
}