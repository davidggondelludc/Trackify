package com.apm.trackify.ui.playlist.details.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding

data class DisplayableFooter(
    override val type: Int = R.layout.playlists_details_footer_item,
    val title: String
) : BaseModel {

    override fun bind(view: View, position: Int) {
        view.apply {
            val binding = PlaylistsDetailsFooterItemBinding.bind(view)

            binding.title.text = title
        }
    }
}