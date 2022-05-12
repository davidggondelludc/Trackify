package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.UiModel

data class TracksResponse(
    val items: List<TrackResponse>,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
) {

    fun toTrackItems(): List<UiModel.TrackItem> {
        val tracks = mutableListOf<UiModel.TrackItem>()
        sequence<UiModel.TrackItem> {
            items.forEach {
                yield(it.toTrackItem())
            }
        }
        items.forEach {
            tracks.add(it.toTrackItem())
        }
        return tracks
    }
}