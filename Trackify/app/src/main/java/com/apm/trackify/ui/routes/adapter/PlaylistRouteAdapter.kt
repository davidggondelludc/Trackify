package com.apm.trackify.ui.routes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.routes.view.PlaylistRouteViewHolder

class PlaylistRouteAdapter() : ListAdapter<Playlist, PlaylistRouteViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistRouteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}