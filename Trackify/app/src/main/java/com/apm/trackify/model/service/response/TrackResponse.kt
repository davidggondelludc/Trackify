package com.apm.trackify.model.service.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.model.service.domain.Track

data class TrackResponse(
    val track: Track
) {

    fun toTrackItem(): TrackItem = track.toTrackItem()
}