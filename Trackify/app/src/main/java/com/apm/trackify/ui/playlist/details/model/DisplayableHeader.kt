package com.apm.trackify.ui.playlist.details.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableHeader(
    override val type: Int = R.layout.playlists_details_header_item,
    val title: String
) : BaseModel