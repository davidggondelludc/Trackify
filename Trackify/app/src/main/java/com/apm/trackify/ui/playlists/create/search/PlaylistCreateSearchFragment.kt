package com.apm.trackify.ui.playlists.create.search

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding
import com.apm.trackify.ui.playlists.tracks.PlaylistTracksViewModel
import com.apm.trackify.ui.playlists.tracks.adapter.AddTrackAdapter

class PlaylistCreateSearchFragment : Fragment() {
    private val viewModel: PlaylistTracksViewModel by viewModels()

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

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.user_fragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        val addTrackAdapter = AddTrackAdapter()

        viewModel.getTracks().observe(viewLifecycleOwner) {
            addTrackAdapter.submitList(it)
        }

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(addTrackAdapter)

        recyclerView.adapter = concatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}