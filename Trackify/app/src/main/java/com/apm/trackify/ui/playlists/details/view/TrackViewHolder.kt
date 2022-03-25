package com.apm.trackify.ui.playlists.details.view

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.TrackDragItemBinding
import com.apm.trackify.extensions.*
import com.apm.trackify.ui.playlists.details.model.Track

class TrackViewHolder(
    private val binding: TrackDragItemBinding,
    itemTouchHelper: ItemTouchHelper
) : RecyclerView.ViewHolder(binding.root) {

    init {
        scaleOnTouch()
        setOnDragListener(binding.drag, itemTouchHelper)
    }

    fun bind(track: Track) {
        // TODO: replace toast with track playback
        binding.root.setOnClickListener { it.context.toast("PLAYBACK ${track.name}") }
        binding.cover.loadFromURI(track.imageUri, R.drawable.placeholder_musical_note)
        binding.name.text = track.name
        binding.artists.text = track.artists
        binding.explicit.toggleVisibility(track.explicit, true)
    }
}