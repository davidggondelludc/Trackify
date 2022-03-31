package com.apm.trackify.ui.playlists.tracks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistTrackHeaderItemBinding
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.tracks.view.HeaderViewHolder

class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    private var playlist: Playlist? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistTrackHeaderItemBinding.inflate(inflater, parent, false)

        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(playlist!!)
    }

    override fun getItemCount(): Int = 1

    fun submit(playlist: Playlist) {
        this.playlist = playlist
        notifyItemChanged(0)
    }
}