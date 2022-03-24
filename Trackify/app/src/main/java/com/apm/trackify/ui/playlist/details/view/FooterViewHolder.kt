package com.apm.trackify.ui.playlist.details.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding

class FooterViewHolder(private val binding: PlaylistsDetailsFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.title.text = title
    }
}