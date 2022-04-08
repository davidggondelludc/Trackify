package com.apm.trackify.ui.playlists.landing.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.landing.view.holder.PlaylistViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class PlaylistAdapter : DelegateAdapter<Playlist, PlaylistViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistViewHolder(binding)
    }
}