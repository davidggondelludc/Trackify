package com.apm.trackify.ui.playlists.details.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding

class HeaderViewHolder(
    val binding: PlaylistsTrackHeaderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val ownerTextView = binding.owner
    val spotifyButton = binding.spotify
    val sortByTextView = binding.sortBy
    val sortOrderButton = binding.sortOrder
}