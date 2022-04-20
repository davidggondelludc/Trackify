package com.apm.trackify.ui.playlists.details.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.details.view.holder.HeaderViewHolder
import com.apm.trackify.util.base.DelegateAdapter
import com.apm.trackify.util.base.DelegateViewHolder

class HeaderAdapter : DelegateAdapter<Playlist, HeaderViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackHeaderItemBinding.inflate(inflater, parent, false)

        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DelegateViewHolder<Playlist>, position: Int) {
        super.onBindViewHolder(holder, position)

        val binding = holder.binding as PlaylistsTrackHeaderItemBinding
        binding.spotify.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(getItem(position).playlistUri)
            startActivity(holder.itemView.context, intent, null)
        }
    }
}