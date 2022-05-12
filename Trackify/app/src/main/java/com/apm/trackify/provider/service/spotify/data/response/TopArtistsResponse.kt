package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.spotify.data.Track

data class TopArtistsResponse(
    val items: List<Track>
) {

    fun toTrackItems(): List<UiModel.TrackItem> {
        val tracks = mutableListOf<UiModel.TrackItem>()
        items.forEach {
            tracks.add(it.toTrackItem())
        }
        return tracks
    }
}