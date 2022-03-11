package com.apm.trackify.ui.playlist.details.model

import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel

data class DisplayableFooter(
    override val type: Int = R.layout.playlists_details_footer_item,
    val title: String
) : BaseModel