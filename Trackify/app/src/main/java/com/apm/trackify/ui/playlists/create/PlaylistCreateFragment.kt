package com.apm.trackify.ui.playlists.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.listener.DragSwipeCallback
import com.apm.trackify.ui.playlists.create.view.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.create.view.adapter.TrackDragAdapter
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel
import com.apm.trackify.ui.playlists.details.view.adapter.FooterAdapter
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toastError
import com.apm.trackify.util.extension.toastSuccess
import com.apm.trackify.util.extension.toggleVisibility
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistCreateFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle
    private val viewModel: PlaylistCreateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsCreateFragmentBinding.bind(view)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.savePlaylist -> {
                    viewModel.savePlaylist {
                        val navController = findNavController()
                        navController.navigateUp()
                        requireContext().toastSuccess(R.string.playlist_save_toast)
                    }
                }
            }
            true
        }
        setupToolbar(binding.toolbar)

        val callback = DragSwipeCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.playlist)

        val headerAdapter = HeaderAdapter(viewModel).apply {
            submitList(listOf(viewModel.playlist))
        }
        val trackDragAdapter = TrackDragAdapter(itemTouchHelper, mediaServiceLifecycle)
        val footerAdapter = FooterAdapter()

        binding.playlist.adapter = ConcatAdapter(headerAdapter, trackDragAdapter, footerAdapter)
        binding.playlist.layoutManager = LinearLayoutManager(context)

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.toggleVisibility(it, false)
            binding.playlist.toggleVisibility(!it, false)
            binding.toolbar.toggleVisibility(!it, true)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            binding.loading.toggleVisibility(visible = false, gone = false)
            requireContext().toastError(it)
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
    }

    private fun generateFooter(tracks: Int, milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)

        when {
            hours == 0L -> {
                return "${
                    requireContext().resources.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    requireContext().resources.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
            minutes == 0L -> {
                return "${
                    requireContext().resources.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    requireContext().resources.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                }"
            }
            else -> {
                return "${
                    requireContext().resources.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    requireContext().resources.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                } ${
                    requireContext().resources.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
        }
    }
}