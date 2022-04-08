package com.apm.trackify.ui.playlists.create.view.holder

import androidx.navigation.findNavController
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.create.PlaylistCreateFragmentDirections
import com.apm.trackify.util.base.DelegateViewHolder

class HeaderViewHolder(private val binding: PlaylistsCreateHeaderBinding) :
    DelegateViewHolder<Playlist>(binding) {

    override fun bind(item: Playlist) {
        binding.search.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistCreateFragmentDirections.toPlaylistCreateSearchFragment()
            navController.navigate(action)
        }
    }
}