package com.apm.trackify.ui.routes.landing.view.holder

import androidx.navigation.findNavController
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.routes.landing.RoutesLandingFragmentDirections
import com.apm.trackify.util.base.DelegateViewHolder

class PlaylistRouteViewHolder(override val binding: RoutesPlaylistsItemBinding) :
    DelegateViewHolder<Playlist>(binding) {

    override fun bind(item: Playlist) {
        binding.imgBtnSeePlayList.setOnClickListener {
            val navController = it.findNavController()
            val action = RoutesLandingFragmentDirections.toPlaylistTrackFragment(item)
            navController.navigate(action)
        }
        binding.tvPlaylistInRoute.text = item.name
        binding.tvUserPlaylistsInRoute.text = item.owner
    }
}