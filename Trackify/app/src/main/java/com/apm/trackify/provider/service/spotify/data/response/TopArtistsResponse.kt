package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.data.Track

data class TopArtistsResponse(
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