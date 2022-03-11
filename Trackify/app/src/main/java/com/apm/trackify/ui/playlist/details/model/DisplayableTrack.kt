package com.apm.trackify.ui.playlist.details.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableTrack(
    override val type: Int = R.layout.playlists_details_track_item,
    val title: String,
    val subtitle: String,
    val explicit: Boolean,
) : BaseModel