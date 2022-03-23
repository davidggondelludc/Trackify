package com.apm.trackify.ui.playlist.details.view

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.extensions.loadFromURI
import com.apm.trackify.extensions.toast
import com.apm.trackify.extensions.toggleVisibility
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding
import com.apm.trackify.ui.playlist.details.model.Track

@SuppressLint("ClickableViewAccessibility")
class TrackViewHolder(
    private val binding: PlaylistsDetailsTrackItemBinding,
    private val itemTouchHelper: ItemTouchHelper
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN ->
                    AnimatorInflater.loadAnimator(view.context, R.animator.scale_in).apply {
                        setTarget(view)
                        start()
                    }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                    AnimatorInflater.loadAnimator(view.context, R.animator.restore).apply {
                        setTarget(view)
                        start()
                    }
            }
            false
        }

        binding.drag.setOnTouchListener { _, motionEvent ->
            when (motionEvent.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    itemTouchHelper.startDrag(this)
                    true
                }
                else -> false
            }
        }
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