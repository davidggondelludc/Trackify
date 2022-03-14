package com.apm.trackify.ui.playlist.details.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.databinding.PlaylistsDetailsHeaderItemBinding

data class DisplayableHeader(
    override val type: Int = R.layout.playlists_details_header_item,
    val title: String
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = PlaylistsDetailsHeaderItemBinding.bind(view)

        view.apply {
            binding.title.text = title
        }
    }
}