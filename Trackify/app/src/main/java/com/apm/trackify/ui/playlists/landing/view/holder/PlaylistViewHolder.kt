package com.apm.trackify.ui.playlists.landing.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class PlaylistViewHolder(
    val binding: PlaylistsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val totalTracksTextView = binding.totalTracks

    init {
        scaleOnTouch()
    }
}