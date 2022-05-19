package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.TrackItem

data class TracksResponse(
    val items: List<TrackResponse>,
    val next: String?,
    val offset: Int,
    val previous: String?,
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