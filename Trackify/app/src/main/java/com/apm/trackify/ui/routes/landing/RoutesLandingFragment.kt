package com.apm.trackify.ui.routes.landing

import android.content.ContentValues.TAG
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.ui.main.MainActivity
import com.apm.trackify.ui.routes.landing.view.adapter.PlaylistRouteAdapter
import com.apm.trackify.ui.routes.landing.view.model.RoutesLandingViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toPx
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
        viewModel.routes.observe(viewLifecycleOwner) {
            playlistRoutesAdapter.submitList(it)
        }

        recyclerView.adapter = playlistRoutesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val heightpx = resources.getDimension(R.dimen.user_mapview_height).toPx.toInt()
        mapUtil = MapsUtil(googleMap, context, resources.displayMetrics.widthPixels, heightpx)
        mapUtil.setDefaultSettings()

        val userCoordinate = LatLng(40.412235968709616, -3.6823606115609073)

        mapUtil.createUserMarker(userCoordinate)
    }

}