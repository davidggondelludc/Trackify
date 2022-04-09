package com.apm.trackify.ui.playlists.details.view.holder

import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding
import com.apm.trackify.util.base.DelegateViewHolder

class FooterViewHolder(override val binding: PlaylistsTrackFooterItemBinding) :
    DelegateViewHolder<String>(binding) {

    override fun bind(item: String) {
        binding.title.text = item
    }
}