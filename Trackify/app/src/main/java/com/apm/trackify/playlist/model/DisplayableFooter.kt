package com.apm.trackify.playlist.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.databinding.ItemPlaylistFooterBinding

data class DisplayableFooter(
    override val type: Int = R.layout.item_playlist_footer,
    val title: String
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = ItemPlaylistFooterBinding.bind(view)

        view.apply {
            binding.title.text = title
        }
    }
}