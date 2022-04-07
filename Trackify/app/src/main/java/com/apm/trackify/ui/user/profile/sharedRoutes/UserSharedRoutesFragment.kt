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
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.apm.trackify.ui.user.profile.sharedRoutes.adapter.UserSharedRouteAdapter

class UserSharedRoutesFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: UserSharedRoutesViewModel by viewModels()
    private lateinit var mapUtil: MapsUtil

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
        val routeAdapter = UserSharedRouteAdapter()
        viewModel.getRoutes().observe(viewLifecycleOwner) {
            routeAdapter.submitList(it)
        }

        recyclerView.adapter = routeAdapter
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