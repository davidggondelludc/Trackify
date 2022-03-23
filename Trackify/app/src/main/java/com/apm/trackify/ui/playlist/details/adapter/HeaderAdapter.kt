package com.apm.trackify.ui.playlist.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsDetailsHeaderItemBinding
import com.apm.trackify.ui.playlist.details.view.HeaderViewHolder
import com.apm.trackify.ui.playlist.model.Playlist

class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    private var playlist: Playlist? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsDetailsHeaderItemBinding.inflate(inflater, parent, false)

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