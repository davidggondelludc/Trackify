package com.apm.trackify.ui.routes.search

import PlaylistRoutesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.databinding.RoutesSearchFragmentBinding
import com.apm.trackify.ui.playlist.details.model.PlaylistCoverItemsViewModel
import com.apm.trackify.ui.playlist.details.model.PlaylistRoutesItemsViewModel

class SearchFragment : Fragment() {

    private lateinit var navc: NavController
    private var _binding: RoutesSearchFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RoutesSearchFragmentBinding.inflate(inflater, container, false)
        val recyclerview = binding.rvPlaylistRoutes

        recyclerview.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<PlaylistRoutesItemsViewModel>()

        for (i in 1..20) {
            data.add(PlaylistRoutesItemsViewModel("Playlist " + i, "User " + i))
        }

        val adapter = PlaylistRoutesAdapter(data)
        recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)
        binding.btnAddPlaylist2route.setOnClickListener {
            navc.navigate(R.id.action_routes_search_to_route_share)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}