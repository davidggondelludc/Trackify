package com.apm.trackify.playlist.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.databinding.ItemPlaylistHeaderBinding

data class DisplayableHeader(
    override val type: Int = R.layout.item_playlist_header,
    val title: String
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = ItemPlaylistHeaderBinding.bind(view)

        view.apply {
            binding.title.text = title
        }
    }
}