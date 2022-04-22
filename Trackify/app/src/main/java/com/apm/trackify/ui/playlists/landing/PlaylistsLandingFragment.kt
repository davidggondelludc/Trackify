package com.apm.trackify.ui.playlists.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.ui.playlists.landing.view.adapter.PlaylistAdapter
import com.apm.trackify.ui.playlists.landing.view.model.PlaylistsLandingViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toast

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
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add -> {
                    val navController = findNavController()
                    val action = PlaylistsLandingFragmentDirections.toPlaylistCreateFragment()
                    navController.navigate(action)
                }
            }
            true
        }
        setupRecyclerView(binding.playlists)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val playlistsAdapter = PlaylistAdapter()
        viewModel.playlists.observe(viewLifecycleOwner) {
            playlistsAdapter.submitList(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            context?.toast(it)
        }

        recyclerView.adapter = playlistsAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }
}