package com.apm.trackify.ui.routes.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesCreatePlaylistItemBinding
import com.apm.trackify.model.diff.PlaylistItemDiffUtil
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.ui.routes.create.view.holder.PlaylistRoutesViewHolder
import com.apm.trackify.util.extension.loadFromURI

class PlaylistRoutesAdapter :
    ListAdapter<PlaylistItem, PlaylistRoutesViewHolder>(PlaylistItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRoutesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesCreatePlaylistItemBinding.inflate(inflater, parent, false)

        return PlaylistRoutesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistRoutesViewHolder, position: Int) {
        val playlist = getItem(position)

        holder.coverImageView.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        holder.nameTextView.text = playlist.name
    }
}