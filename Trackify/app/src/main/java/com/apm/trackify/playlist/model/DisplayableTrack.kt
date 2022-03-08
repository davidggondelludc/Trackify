package com.apm.trackify.playlist.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableTrack(
    override val type: Int = R.layout.item_playlist_track,
    val title: String,
    val subtitle: String,
    val explicit: Boolean
) : BaseModel