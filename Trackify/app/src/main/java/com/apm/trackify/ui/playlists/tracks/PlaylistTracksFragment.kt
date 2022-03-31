package com.apm.trackify.ui.playlists.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistTracksFragmentBinding
import com.apm.trackify.ui.playlists.tracks.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.drag.ItemTouchHelperCallback

class PlaylistTracksFragment : Fragment() {

    private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistTracksFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistTracksFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.playlist)
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.profile_fragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val callback = ItemTouchHelperCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val headerAdapter = HeaderAdapter()
        viewModel.getPlaylist().observe(viewLifecycleOwner) {
            headerAdapter.submit(it)
        }

        val trackAdapter = TrackAdapter(itemTouchHelper)

        val footerAdapter = FooterAdapter()
        viewModel.getTracks().observe(viewLifecycleOwner) {
            trackAdapter.submitList(it)
            footerAdapter.submit(it.size, it.sumOf { track -> track.duration })
        }

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(headerAdapter)
        concatAdapter.addAdapter(trackAdapter)
        concatAdapter.addAdapter(footerAdapter)

        recyclerView.adapter = concatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}