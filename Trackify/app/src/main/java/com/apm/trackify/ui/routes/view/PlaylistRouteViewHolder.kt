package com.apm.trackify.ui.routes.view

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist

class PlaylistRouteViewHolder(val binding: RoutesPlaylistsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.imgBtnSeePlayList.setOnClickListener {
            val navController = Navigation.findNavController(binding.root)
            navController.navigate(R.id.action_routes_search_to_playlist_details)
        }
    }

    fun bind(playlist: Playlist) {
        binding.tvPlaylistInRoute.text = playlist.name
        binding.tvUserPlaylistsInRoute.text = playlist.owner
    }
}