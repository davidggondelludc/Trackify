package com.apm.trackify.ui.playlists.tracks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.apm.trackify.util.base.DelegateAdapter
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.tracks.view.holder.TrackViewHolder

class TrackAdapter(private val itemTouchHelper: ItemTouchHelper) :
    DelegateAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackDragItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding, itemTouchHelper)
    }
}