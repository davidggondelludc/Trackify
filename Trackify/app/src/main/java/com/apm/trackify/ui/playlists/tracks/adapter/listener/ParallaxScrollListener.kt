package com.apm.trackify.ui.playlists.tracks.adapter.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import kotlin.math.abs

class ParallaxScrollListener(private val parallax: Float = 0.7f) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        if (layoutManager.findFirstVisibleItemPosition() > 0) return

        val holder = recyclerView.findViewHolderForAdapterPosition(0)
        holder?.let {
            val binding = PlaylistsTrackHeaderItemBinding.bind(it.itemView)
            val diff = binding.cover.height - abs(binding.wrapper.height - binding.root.bottom)

            binding.cover.translationY = diff.toFloat() * parallax
        }
    }
}