package com.apm.trackify.ui.playlists.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding
import com.apm.trackify.ui.playlists.tracks.PlaylistTracksViewModel
import com.apm.trackify.ui.playlists.tracks.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.tracks.adapter.drag.ItemTouchHelperCallback

class PlaylistCreateFragment : Fragment() {
    private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        binding.formSearchButton.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.playlist_create_fragment_to_playlist_create_search_fragment)
        }

        setupRecyclerView(binding.rvSelectedSongs)
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
        toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.savePlaylist -> {
                    navController.navigateUp()
                }
            }
            true
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val callback = ItemTouchHelperCallback(viewModel)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        val trackAdapter = TrackAdapter(itemTouchHelper)

        viewModel.getTracks().observe(viewLifecycleOwner) {
            trackAdapter.submitList(it)
        }

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(trackAdapter)

        recyclerView.adapter = concatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}