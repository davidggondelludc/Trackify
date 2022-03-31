package com.apm.trackify.ui.playlists.view

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.util.extension.getQuantityString
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.scaleOnTouch

class PlaylistViewHolder(val binding: PlaylistsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        scaleOnTouch()

        binding.root.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.playlists_fragment_to_playlist_track_fragment)
        }
    }

    fun bind(playlist: Playlist) {
        binding.cover.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        binding.name.text = playlist.name
        binding.totalTracks.text =
            getQuantityString(R.plurals.tracks, playlist.totalTracks, playlist.totalTracks)
    }
}