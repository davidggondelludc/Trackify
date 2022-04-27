package com.apm.trackify.ui.routes.create.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.RoutesCreatePlaylistItemBinding
import com.apm.trackify.model.domain.Playlist

class PlaylistRoutesViewHolder (val binding: RoutesCreatePlaylistItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val checkBox = binding.checkbox

    fun bind(playlist: Playlist) {
        binding.name.text = playlist.name
    }
}