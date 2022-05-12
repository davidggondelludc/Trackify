package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.spotify.data.Track

data class TrackResponse(
    val track: Track
) {

    fun toTrackItem(): UiModel.TrackItem = track.toTrackItem()
}