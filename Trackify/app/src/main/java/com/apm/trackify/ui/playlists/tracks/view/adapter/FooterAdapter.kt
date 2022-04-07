package com.apm.trackify.ui.playlists.tracks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.base.DelegateAdapter
import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding
import com.apm.trackify.model.diff.StringDiffUtil
import com.apm.trackify.ui.playlists.tracks.view.holder.FooterViewHolder

class FooterAdapter : DelegateAdapter<String, FooterViewHolder>(StringDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackFooterItemBinding.inflate(inflater, parent, false)

        return FooterViewHolder(binding)
    }
}