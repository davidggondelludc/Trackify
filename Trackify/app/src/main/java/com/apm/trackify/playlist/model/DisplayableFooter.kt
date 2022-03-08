package com.apm.trackify.playlist.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableFooter(
    override val type: Int = R.layout.item_playlist_footer,
    val title: String
) : BaseModel