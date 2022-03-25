package com.apm.trackify.ui.playlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistItemBinding
import com.apm.trackify.ui.playlists.model.Playlist
import com.apm.trackify.ui.playlists.model.diff.PlaylistDiffUtil
import com.apm.trackify.ui.playlists.view.PlaylistViewHolder

class PlaylistsAdapter() : ListAdapter<Playlist, PlaylistViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistItemBinding.inflate(inflater, parent, false)

        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
