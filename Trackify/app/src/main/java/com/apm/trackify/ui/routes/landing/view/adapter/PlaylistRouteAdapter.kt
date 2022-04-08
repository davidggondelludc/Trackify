package com.apm.trackify.ui.routes.landing.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.routes.landing.view.holder.PlaylistRouteViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class PlaylistRouteAdapter :
    DelegateAdapter<Playlist, PlaylistRouteViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistRouteViewHolder(binding)
    }
}