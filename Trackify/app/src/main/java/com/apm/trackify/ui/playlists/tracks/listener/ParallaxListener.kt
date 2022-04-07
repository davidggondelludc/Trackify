package com.apm.trackify.ui.playlists.tracks.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import kotlin.math.abs

class ParallaxListener(private val parallax: Float = 0.7f) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        if (layoutManager.findFirstVisibleItemPosition() > 0) return

        recyclerView.findViewHolderForAdapterPosition(0)?.apply {
            val binding = PlaylistsTrackHeaderItemBinding.bind(itemView)
            val diff = binding.cover.height - abs(binding.wrapper.height - binding.root.bottom)

            binding.cover.translationY = diff.toFloat() * parallax
        }
    }
}