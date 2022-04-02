package com.apm.trackify.ui.playlists.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsTracksFragmentBinding
import com.apm.trackify.ui.playlists.tracks.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.drag.ItemTouchHelperCallback
import com.apm.trackify.ui.playlists.tracks.adapter.listener.ParallaxScrollListener
import com.apm.trackify.util.extension.setupToolbar

class PlaylistTracksFragment : Fragment() {

    private val args: PlaylistTracksFragmentArgs by navArgs()
    private val viewModel: PlaylistTracksViewModel by viewModels()
    private val parallaxScrollListener = ParallaxScrollListener()

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
        val callback = ItemTouchHelperCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // TODO: Create view model factory
        val headerAdapter = HeaderAdapter().apply { submit(args.playlist) }
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
        recyclerView.addOnScrollListener(parallaxScrollListener)
    }
}