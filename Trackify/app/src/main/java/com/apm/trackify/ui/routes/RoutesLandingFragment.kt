package com.apm.trackify.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.ui.routes.adapter.PlaylistRouteAdapter

class RoutesLandingFragment : Fragment() {

    private val viewModel: RoutesLandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RoutesLandingFragmentBinding.inflate(inflater, container, false)

        setUpRecyclerView(binding.rvPlaylistRoutes)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = RoutesLandingFragmentBinding.bind(view)

        binding.btnAddPlaylist2route.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_routes_search_to_route_share)
        }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val playlistRoutesAdapter = PlaylistRouteAdapter()
        viewModel.getPlaylists().observe(viewLifecycleOwner) {
            playlistRoutesAdapter.submitList(it)
        }

        recyclerView.adapter = playlistRoutesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}