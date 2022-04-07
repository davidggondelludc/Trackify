package com.apm.trackify.ui.routes.view

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.routes.RoutesLandingFragmentDirections

class PlaylistRouteViewHolder(val binding: RoutesPlaylistsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(playlist: Playlist) {
        binding.imgBtnSeePlayList.setOnClickListener {
            val navController = it.findNavController()
            val action = RoutesLandingFragmentDirections.toPlaylistTrackFragment(playlist)
            navController.navigate(action)
        }
        binding.tvPlaylistInRoute.text = playlist.name
        binding.tvUserPlaylistsInRoute.text = playlist.owner
    }
}