package com.apm.trackify.ui.playlists.details.view.holder

import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding

class FooterViewHolder(private val binding: PlaylistsTrackFooterItemBinding) :
    DelegateViewHolder<String>(binding.root) {

    override fun bind(item: String) {
        binding.title.text = item
    }
}