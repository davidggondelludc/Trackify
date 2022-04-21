package com.apm.trackify.ui.playlists.create.view.holder

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.util.extension.scaleOnTouch
import com.apm.trackify.util.extension.setOnDragListener

class TrackDragViewHolder(
    val binding: PlaylistsTrackDragItemBinding,
    itemTouchHelper: ItemTouchHelper
) : RecyclerView.ViewHolder(binding.root) {

    val coverImageView = binding.cover
    val nameTextView = binding.name
    val explicitImageView = binding.explicit
    val artistsTextView = binding.artists

    init {
        scaleOnTouch()
        setOnDragListener(binding.drag, itemTouchHelper)
    }
}