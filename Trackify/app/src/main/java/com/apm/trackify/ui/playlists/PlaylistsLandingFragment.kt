package com.apm.trackify.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
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

        setUpRecyclerView(binding.playlists)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PlaylistsLandingFragmentBinding.bind(view)
        binding.create.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_landing_to_navigation_create)
        }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
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