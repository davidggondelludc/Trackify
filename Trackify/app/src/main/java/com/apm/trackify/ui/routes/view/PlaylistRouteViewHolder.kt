package com.apm.trackify.ui.routes.view

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist

class PlaylistRouteViewHolder(val binding: RoutesPlaylistsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.imgBtnSeePlayList.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.routes_fragment_to_playlist_track_fragment)
        }
    }

    fun bind(playlist: Playlist) {
        binding.tvPlaylistInRoute.text = playlist.name
        binding.tvUserPlaylistsInRoute.text = playlist.owner
    }
}