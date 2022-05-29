package com.apm.trackify.ui.routes.landing

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.provider.service.spotify.SpotifyApi
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutesLandingFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var spotifyApi: SpotifyApi
    private val viewModel: RoutesLandingViewModel by viewModels()
    private var latitude: Double = 42.73699753499026
    private var longitude: Double = -5.486167589053743

    private lateinit var mapUtil: MapsUtil

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RoutesLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
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
        val playlistRoutesAdapter = PlaylistRouteAdapter(spotifyApi) { coordinates: List<LatLng> ->
            mapUtil.drawRouteAndSetOnClick(
                coordinates
            )
        }
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

        getLastLocation()
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful && taskLocation.result != null) {
                    val location = taskLocation.result
                    latitude = location.latitude
                    longitude = location.longitude
                    val userCoordinate = LatLng(latitude, longitude)
                    mapUtil.createUserMarker(userCoordinate)
                } else {

                    Log.w(TAG, "getLastLocation:exception", taskLocation.exception)
                    Toast.makeText(
                        requireContext(),
                        "No Location Detected. Make sure permissions are granted",
                        Toast.LENGTH_SHORT
                    ).show()
                    val navController = findNavController()
                    navController.navigateUp()

                }
            }
    }

}