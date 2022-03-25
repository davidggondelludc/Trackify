package com.apm.trackify.ui.playlists.navigation.tracks

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
import com.apm.trackify.databinding.PlaylistsDetailsFragmentBinding
import com.apm.trackify.ui.playlists.navigation.tracks.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.navigation.tracks.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.navigation.tracks.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.navigation.tracks.adapter.drag.ItemTouchHelperCallback

class PlaylistTracksFragment : Fragment() {

    private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PlaylistsDetailsFragmentBinding.inflate(inflater, container, false)

        setUpRecyclerView(binding.playlist)

        return binding.root
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
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