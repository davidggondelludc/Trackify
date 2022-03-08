package com.apm.trackify.playlist.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableHeader(
    override val type: Int = R.layout.item_playlist_header,
    val title: String
) : BaseModel