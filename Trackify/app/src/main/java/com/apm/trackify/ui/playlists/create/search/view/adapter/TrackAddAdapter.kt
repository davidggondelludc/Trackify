package com.apm.trackify.ui.playlists.create.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.create.search.view.holder.TrackAddViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class TrackAddAdapter : DelegateAdapter<Track, TrackAddViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAddViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackAddItemBinding.inflate(inflater, parent, false)

        return TrackAddViewHolder(binding)
    }
}