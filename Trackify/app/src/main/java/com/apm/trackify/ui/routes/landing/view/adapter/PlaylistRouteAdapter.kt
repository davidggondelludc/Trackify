package com.apm.trackify.ui.routes.landing.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.diff.PlaylistItemDiffUtil
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.ui.routes.landing.RoutesLandingFragmentDirections
import com.apm.trackify.ui.routes.landing.view.holder.PlaylistRouteViewHolder
import com.apm.trackify.util.extension.loadFromURI

class PlaylistRouteAdapter :
    ListAdapter<PlaylistItem, PlaylistRouteViewHolder>(PlaylistItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistRouteViewHolder, position: Int) {
        val playlist = getItem(position)

        holder.coverImageView.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        holder.nameTextView.text = playlist.name
        holder.ownerTextView.text = playlist.owner

        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            val action = RoutesLandingFragmentDirections.toPlaylistTrackFragment(playlist)
            navController.navigate(action)
        }
    }
}