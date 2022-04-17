package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.create.view.holder.HeaderViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class HeaderAdapter : DelegateAdapter<Playlist, HeaderViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsCreateHeaderBinding.inflate(inflater, parent, false)

        return HeaderViewHolder(binding)
    }
}