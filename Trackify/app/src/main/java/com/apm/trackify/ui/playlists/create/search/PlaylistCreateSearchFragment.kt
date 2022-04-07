package com.apm.trackify.ui.playlists.create.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding
import com.apm.trackify.ui.playlists.tracks.adapter.AddTrackAdapter
import com.apm.trackify.util.extension.setupToolbar

class PlaylistCreateSearchFragment : Fragment() {

    //private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsCreateSearchFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        setupRecyclerView(binding.rvSearchedSongs)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        val addTrackAdapter = AddTrackAdapter()

        //viewModel.getTracks().observe(viewLifecycleOwner) {
        //    addTrackAdapter.submitList(it)
        //}

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(addTrackAdapter)

        recyclerView.adapter = concatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}