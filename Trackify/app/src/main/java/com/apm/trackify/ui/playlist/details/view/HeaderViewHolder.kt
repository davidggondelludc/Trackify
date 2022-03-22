package com.apm.trackify.ui.playlist.details.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.extensions.loadFromURI
import com.apm.trackify.extensions.toast
import com.apm.trackify.databinding.PlaylistsDetailsHeaderItemBinding
import com.apm.trackify.ui.playlist.model.Playlist

class HeaderViewHolder(private val binding: PlaylistsDetailsHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(playlist: Playlist) {
        binding.cover.loadFromURI(playlist.imageUri, R.drawable.placeholder_album)
        binding.name.text = playlist.name

        // TODO: replace toasts with correct interaction
        binding.spotify.setOnClickListener { it.context.toast("GO TO SPOTIFY") }
        binding.unfollow.setOnClickListener { it.context.toast("UNFOLLOW USER") }
    }
}