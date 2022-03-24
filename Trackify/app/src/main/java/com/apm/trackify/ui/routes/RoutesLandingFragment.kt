package com.apm.trackify.ui.routes

import com.apm.trackify.ui.routes.adapter.PlaylistRoutesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.ui.routes.model.PlaylistRoutesItemsViewModel

class RoutesLandingFragment : Fragment() {

    private lateinit var navc: NavController
    private var _binding: RoutesLandingFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RoutesLandingFragmentBinding.inflate(inflater, container, false)
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