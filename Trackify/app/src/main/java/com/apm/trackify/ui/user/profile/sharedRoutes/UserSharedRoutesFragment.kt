package com.apm.trackify.ui.user.profile.sharedRoutes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserSharedRoutesFragmentBinding
import com.apm.trackify.ui.user.profile.sharedRoutes.adapter.SharedRouteAdapter
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Double.min
import kotlin.math.max

class UserSharedRoutesFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: UserSharedRoutesViewModel by viewModels()
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserSharedRoutesFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserSharedRoutesFragmentBinding.bind(view)

        setupRecyclerView(binding.rvSharedRouteItems)

        binding.mapViewFragment.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val routeAdapter = SharedRouteAdapter()
        viewModel.getRoutes().observe(viewLifecycleOwner) {
            routeAdapter.submitList(it)
        }

        recyclerView.adapter = routeAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(false)
        map.isTrafficEnabled = true

        val latitude = 43.371023
        val longitude = -8.405215
        val lat2 = 43.382825
        val long2 = -8.410223
        val coordinates = LatLng( latitude, longitude)
        val coords2 = LatLng(lat2, long2)
        createMarker(coordinates)
        createMarker(coords2)
        val offset = 0.003
        val myBounds = LatLngBounds(
            LatLng(min(latitude, lat2), min(longitude, long2)),
            LatLng(max(latitude, lat2) + offset, max(longitude, long2))
        )
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(myBounds, 5))
    }

    private fun midPoint(lat1: Double, long1: Double, lat2: Double, long2: Double): LatLng {
        return LatLng((lat1 + lat2) / 2, (long1 + long2) / 2)
    }

    private fun createMarker(coords: LatLng) {
        val marker: MarkerOptions = MarkerOptions().position(coords)
        map.addMarker(marker)
    }
}