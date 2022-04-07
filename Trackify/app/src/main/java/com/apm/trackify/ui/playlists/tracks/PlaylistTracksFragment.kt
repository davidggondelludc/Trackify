package com.apm.trackify.ui.playlists.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTracksFragmentBinding
import com.apm.trackify.ui.playlists.tracks.listener.DragSwipeCallback
import com.apm.trackify.ui.playlists.tracks.listener.ParallaxListener
import com.apm.trackify.ui.playlists.tracks.view.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.tracks.view.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.tracks.view.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.tracks.view.model.PlaylistTracksViewModel
import com.apm.trackify.util.extension.setupToolbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PlaylistTracksFragment : Fragment() {

    private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsTracksFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsTracksFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.playlist)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val callback = DragSwipeCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val headerAdapter = HeaderAdapter()
        viewModel.getPlaylist().observe(viewLifecycleOwner) {
            headerAdapter.submitList(listOf(it))
        }

        val trackAdapter = TrackAdapter(itemTouchHelper)

        val footerAdapter = FooterAdapter()
        viewModel.getTracks().observe(viewLifecycleOwner) {
            trackAdapter.submitList(it)
            footerAdapter.submitList(
                listOf(
                    generateFooter(
                        it.size,
                        it.sumOf { track -> track.duration }.toLong()
                    )
                )
            )
        }

        recyclerView.adapter = ConcatAdapter(headerAdapter, trackAdapter, footerAdapter)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addOnScrollListener(ParallaxListener())
    }

    private fun generateFooter(tracks: Int, milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)

        when {
            hours == 0L -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
            minutes == 0L -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                }"
            }
            else -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                } ${
                    context?.resources?.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
        }
    }
}