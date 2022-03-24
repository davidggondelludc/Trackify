package com.apm.trackify.ui.playlist.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsDetailsFragmentBinding
import com.apm.trackify.ui.playlist.details.adapter.FooterAdapter
import com.apm.trackify.ui.playlist.details.adapter.HeaderAdapter
import com.apm.trackify.ui.playlist.details.adapter.TrackAdapter
import com.apm.trackify.ui.playlist.details.adapter.drag.ItemTouchHelperCallback

class PlaylistDetailsFragment : Fragment() {

    private val viewModel: PlaylistDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PlaylistsDetailsFragmentBinding.inflate(inflater, container, false)

        setUpRecyclerView(binding.playlist)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PlaylistsDetailsFragmentBinding.bind(view)

        val navController = Navigation.findNavController(view)
        binding.back.setOnClickListener { navController.navigate(R.id.back_navigation_landing) }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val callback = ItemTouchHelperCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val headerAdapter = HeaderAdapter()
        val trackAdapter = TrackAdapter(itemTouchHelper)
        val footerAdapter = FooterAdapter()

        viewModel.getPlaylist().observe(viewLifecycleOwner) {
            headerAdapter.submit(it)
        }
        viewModel.getTracks().observe(viewLifecycleOwner) {
            trackAdapter.submitList(it)
            footerAdapter.submit(it.size, it.sumOf { track -> track.duration })
        }

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(headerAdapter)
        concatAdapter.addAdapter(trackAdapter)
        concatAdapter.addAdapter(footerAdapter)
        recyclerView.adapter = concatAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
}