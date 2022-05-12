package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.spotify.data.Track

data class SearchResponse(
    val tracks: SearchTracksResponse
) {

    fun toTrackItems(): List<UiModel.TrackItem> {
        return tracks.toTrackItems()
    }

    data class SearchTracksResponse(
        val items: List<Track>,
        val total: Int
    ) {

        fun toTrackItems(): List<UiModel.TrackItem> {
            val tracks = mutableListOf<UiModel.TrackItem>()
            items.forEach {
                tracks.add(it.toTrackItem())
            }
            return tracks
        }
    }
}