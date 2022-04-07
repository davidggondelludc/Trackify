package com.apm.trackify.ui.routes.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.ui.routes.landing.view.model.RoutesLandingViewModel
import com.apm.trackify.ui.routes.landing.view.adapter.PlaylistRouteAdapter
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class RoutesLandingFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: RoutesLandingViewModel by viewModels()
    private lateinit var mapUtil: MapsUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RoutesLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RoutesLandingFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add -> {
                    val navController = findNavController()
                    val action = RoutesLandingFragmentDirections.toRouteCreateFragment()
                    navController.navigate(action)
                }
            }
            true
        }
        binding.mapView.getFragment<SupportMapFragment>().getMapAsync(this)

        setupRecyclerView(binding.rvPlaylistRoutes)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val playlistRoutesAdapter = PlaylistRouteAdapter()
        viewModel.getPlaylists().observe(viewLifecycleOwner) {
            playlistRoutesAdapter.submitList(it)
        }

        recyclerView.adapter = playlistRoutesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapUtil = MapsUtil(googleMap, context)
        mapUtil.setDefaultSettings()

        val coordinates = listOf<LatLng>(
            LatLng(43.371023, -8.405215), LatLng(43.382825, -8.410223),
            LatLng(43.365160, -8.374968), LatLng(43.364100, -8.399088),
            LatLng(43.358961, -8.401851)
        )

        mapUtil.drawRouteAndSetOnClick(coordinates)
    }
}