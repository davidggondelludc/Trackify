package com.apm.trackify.ui.playlists.create.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.search.view.adapter.TrackAddAdapter
import com.apm.trackify.ui.playlists.create.search.view.model.PlaylistCreateSearchViewModel
import com.apm.trackify.util.extension.setupToolbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistCreateSearchFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle
    private val viewModel: PlaylistCreateSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsCreateSearchFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.rvSearchedSongs)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val addTrackAdapter = TrackAddAdapter(mediaServiceLifecycle)
        viewModel.tracks.observe(viewLifecycleOwner) {
            addTrackAdapter.submitList(it)
        }

        recyclerView.adapter = addTrackAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}