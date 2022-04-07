package com.apm.trackify.ui.playlists.tracks.view.holder

import com.apm.trackify.R
import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toast

class HeaderViewHolder(private val binding: PlaylistsTrackHeaderItemBinding) :
    DelegateViewHolder<Playlist>(binding.root) {

    override fun bind(item: Playlist) {
        binding.cover.loadFromURI(item.imageUri, R.drawable.placeholder_playlist)
        binding.name.text = item.name
        binding.owner.text = item.owner

        // TODO: replace toasts with correct interaction
        binding.spotify.setOnClickListener { it.context.toast("GO TO SPOTIFY") }
        binding.unfollow.setOnClickListener { it.context.toast("UNFOLLOW") }
    }
}