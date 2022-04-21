package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding
import com.apm.trackify.model.diff.StringDiffUtil
import com.apm.trackify.ui.playlists.details.view.holder.FooterViewHolder

class FooterAdapter : ListAdapter<String, FooterViewHolder>(StringDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackFooterItemBinding.inflate(inflater, parent, false)

        return FooterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        val title = getItem(position)

        holder.titleTextView.text = title
    }
}