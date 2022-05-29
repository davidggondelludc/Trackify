package com.apm.trackify.ui.playlists.create.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding

class HeaderViewHolder(
    val binding: PlaylistsCreateHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    val nameEditText = binding.name
    val durationSpinner = binding.timeSpinner
    val searchButton = binding.search
}