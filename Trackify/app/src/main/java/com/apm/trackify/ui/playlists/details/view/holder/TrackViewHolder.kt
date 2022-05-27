package com.apm.trackify.ui.playlists.details.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class TrackViewHolder(
    val binding: PlaylistsTrackItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val artistsTextView = binding.artists
    val explicitImageView = binding.explicit

    init {
        scaleOnTouch()
    }
}