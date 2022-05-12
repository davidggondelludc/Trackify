package com.apm.trackify.ui.playlists.details.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding

class FooterViewHolder(val binding: PlaylistsTrackFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val descriptionTextView = binding.description
}