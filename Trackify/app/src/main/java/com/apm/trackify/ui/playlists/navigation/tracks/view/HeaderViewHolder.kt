package com.apm.trackify.ui.playlists.navigation.tracks.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistTrackHeaderItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toast

class HeaderViewHolder(private val binding: PlaylistTrackHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(playlist: Playlist) {
        binding.cover.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        binding.name.text = playlist.name
        binding.owner.text = playlist.owner

        // TODO: replace toasts with correct interaction
        binding.spotify.setOnClickListener { it.context.toast("GO TO SPOTIFY") }
        binding.unfollow.setOnClickListener { it.context.toast("UNFOLLOW") }
    }
}