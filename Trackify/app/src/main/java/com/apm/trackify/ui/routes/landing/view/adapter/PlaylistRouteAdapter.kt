package com.apm.trackify.ui.routes.landing.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.diff.RouteItemDiffUtil
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.domain.RouteItem
import com.apm.trackify.ui.routes.landing.RoutesLandingFragmentDirections
import com.apm.trackify.ui.routes.landing.view.holder.PlaylistRouteViewHolder
import com.apm.trackify.util.extension.loadFromURI

class PlaylistRouteAdapter : ListAdapter<RouteItem, PlaylistRouteViewHolder>(RouteItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistRouteViewHolder, position: Int) {
        val route = getItem(position)

        //val playlist = getPlaylistFromUrl(route.playlistUrl})
        val list: List<PlaylistItem> = MockProvider.playlists
        val playlist = list[position]

        val image = playlist.imageUri

        holder.coverImageView.loadFromURI(image, R.drawable.placeholder_playlist)
        holder.nameTextView.text = route.title
        holder.ownerTextView.text = route.creator

        holder.more.setOnClickListener {
            val navController = it.findNavController()
            val action = RoutesLandingFragmentDirections.toPlaylistTrackFragment(playlist)
            navController.navigate(action)
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, "Show route on the map", Toast.LENGTH_SHORT).show()
        }

    }
}