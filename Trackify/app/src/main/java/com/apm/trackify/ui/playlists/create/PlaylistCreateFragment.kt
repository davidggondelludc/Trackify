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
import com.apm.trackify.ui.playlists.create.listener.DragSwipeCallback
import com.apm.trackify.ui.playlists.create.view.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.create.view.adapter.TrackDragAdapter
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel
import com.apm.trackify.util.extension.setupToolbar

class PlaylistCreateFragment : Fragment() {

    private val viewModel: PlaylistCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        viewModel.getPlaylist().observe(viewLifecycleOwner) {
            headerAdapter.submit(it)
        }

        val trackDragAdapter = TrackDragAdapter(itemTouchHelper)
        viewModel.getTracks().observe(viewLifecycleOwner) {
            trackDragAdapter.submitList(it)
        }

        recyclerView.adapter = ConcatAdapter(headerAdapter, trackDragAdapter)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}