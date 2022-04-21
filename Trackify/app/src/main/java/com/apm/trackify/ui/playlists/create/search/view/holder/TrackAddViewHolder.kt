package com.apm.trackify.ui.playlists.create.search.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class TrackAddViewHolder(val binding: PlaylistsTrackAddItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val explicitImageView = binding.explicit
    val artistsTextView = binding.artists
    val addButton = binding.add

    init {
        scaleOnTouch()
    }
}