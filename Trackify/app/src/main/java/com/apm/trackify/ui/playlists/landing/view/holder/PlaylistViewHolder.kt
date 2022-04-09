package com.apm.trackify.ui.playlists.landing.view.holder

import androidx.navigation.findNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.landing.PlaylistsLandingFragmentDirections
import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.scaleOnTouch

class PlaylistViewHolder(override val binding: PlaylistsItemBinding) :
    DelegateViewHolder<Playlist>(binding) {

    init {
        scaleOnTouch()
    }

    override fun bind(item: Playlist) {
        binding.root.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistsLandingFragmentDirections.toPlaylistTrackFragment(item)
            navController.navigate(action)
        }

        binding.cover.loadFromURI(item.imageUri, R.drawable.placeholder_playlist)
        binding.name.text = item.name
        binding.totalTracks.text =
            resources.getQuantityString(R.plurals.tracks, item.totalTracks, item.totalTracks)
    }
}