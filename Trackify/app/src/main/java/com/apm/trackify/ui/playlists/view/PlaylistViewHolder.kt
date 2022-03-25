package com.apm.trackify.ui.playlists.view

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistItemBinding
import com.apm.trackify.extensions.getQuantityString
import com.apm.trackify.extensions.loadFromURI
import com.apm.trackify.extensions.scaleOnTouch
import com.apm.trackify.ui.playlists.model.Playlist

class PlaylistViewHolder(val binding: PlaylistItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        scaleOnTouch()

        binding.root.setOnClickListener {
            val navController = Navigation.findNavController(binding.root)
            navController.navigate(R.id.action_navigation_landing_to_navigation_details)
        }
    }

    fun bind(playlist: Playlist) {
        binding.cover.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        binding.name.text = playlist.name
        binding.totalTracks.text = getQuantityString(R.plurals.tracks, playlist.totalTracks, playlist.totalTracks)
    }
}