package com.apm.trackify.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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
    ): View {
        val binding = PlaylistsLandingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsLandingFragmentBinding.bind(view)

        setUpRecyclerView(binding.playlists)

        binding.create.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.playlists_fragment_to_playlist_create_fragment)
        }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val playlistsAdapter = PlaylistAdapter()

        // Subscribe to state
        viewModel.getPlaylists().observe(viewLifecycleOwner) {
            playlistsAdapter.submitList(it)
        }

        recyclerView.adapter = playlistsAdapter
        recyclerView.layoutManager = GridLayoutManager(
            context, 2, RecyclerView.VERTICAL, false
        )
    }
}