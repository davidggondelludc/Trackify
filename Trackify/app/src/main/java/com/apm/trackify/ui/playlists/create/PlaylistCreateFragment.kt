package com.apm.trackify.ui.playlists.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.listener.DragSwipeCallback
import com.apm.trackify.ui.playlists.create.view.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.create.view.adapter.TrackDragAdapter
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel
import com.apm.trackify.ui.playlists.details.view.adapter.FooterAdapter
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistCreateFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle
    private val viewModel: PlaylistCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.savePlaylist -> {
                    val navController = findNavController()
                    navController.navigateUp()
                }
            }
            true
        }
        setupRecyclerView(binding.rvSelectedSongs)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val callback = DragSwipeCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val headerAdapter = HeaderAdapter()
        val trackDragAdapter = TrackDragAdapter(itemTouchHelper, mediaServiceLifecycle)
        val footerAdapter = FooterAdapter()

        viewModel.error.observe(viewLifecycleOwner) {
            context?.toast(it)
        }

        viewModel.playlist.observe(viewLifecycleOwner) {
            headerAdapter.submitList(listOf(it))
        }

        viewModel.tracks.observe(viewLifecycleOwner) {
            trackDragAdapter.submitList(it)
            footerAdapter.submitList(
                listOf(
                    generateFooter(
                        it.size,
                        it.sumOf { track -> track.duration }.toLong()
                    )
                )
            )
        }

        recyclerView.adapter = ConcatAdapter(headerAdapter, trackDragAdapter, footerAdapter)
        recyclerView.layoutManager = LinearLayoutManager(context)
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