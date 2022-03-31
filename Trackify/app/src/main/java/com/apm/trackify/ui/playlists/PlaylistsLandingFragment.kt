package com.apm.trackify.ui.playlists

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.ui.playlists.adapter.PlaylistAdapter

class PlaylistsLandingFragment : Fragment() {

    private val viewModel: PlaylistsLandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsLandingFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.playlists)
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
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add -> {
                    navController.navigate(R.id.playlists_fragment_to_playlist_create_fragment)
                }
            }
            true
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val playlistsAdapter = PlaylistAdapter()
        viewModel.getPlaylists().observe(viewLifecycleOwner) {
            playlistsAdapter.submitList(it)
        }

        recyclerView.adapter = playlistsAdapter
        recyclerView.layoutManager = GridLayoutManager(
            context, 2, RecyclerView.VERTICAL, false
        )
    }
}