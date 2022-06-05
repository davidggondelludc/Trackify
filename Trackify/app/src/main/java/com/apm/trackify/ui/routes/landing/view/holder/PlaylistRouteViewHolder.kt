package com.apm.trackify.ui.routes.landing.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class PlaylistRouteViewHolder(
    val binding: RoutesPlaylistsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val ownerTextView = binding.owner
    val more = binding.more1
    val profile = binding.btnSeeProfile
    val icon = binding.selected

    init {
        scaleOnTouch()
    }
}