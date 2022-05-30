package com.apm.trackify.ui.user.landing.sharedRoutes

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
import com.apm.trackify.databinding.UserSharedRoutesFragmentBinding
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter.UserSharedRouteAdapter
import com.apm.trackify.ui.user.landing.sharedRoutes.view.model.UserSharedRoutesViewModel
import com.apm.trackify.util.extension.toPx
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class UserSharedRoutesFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: UserSharedRoutesViewModel by viewModels()

    @Inject
    lateinit var spotifyApi: SpotifyApi
    private lateinit var mapUtil: MapsUtil
    lateinit var binding: UserSharedRoutesFragmentBinding
    lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserSharedRoutesFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = UserSharedRoutesFragmentBinding.bind(view)

        setupRecyclerView(binding.rvSharedRouteItems)

        val properties = Properties()
        val mapViewBundle: Bundle? =
            savedInstanceState?.getBundle(properties.getProperty("MAPS_API_KEY"))
        mapView = binding.mapViewFragment
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
        viewModel.findRoutes()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val navController = findNavController()
        val routeAdapter =
            UserSharedRouteAdapter(
                { viewModel.findRoutes() },
                spotifyApi,
                navController,
                { coordinates: List<LatLng> -> mapUtil.drawRouteAndSetOnClick(coordinates) }
            )
        viewModel.routes.observe(viewLifecycleOwner) {
            routeAdapter.submitList(it)
        }

        recyclerView.adapter = routeAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val heightpx = resources.getDimension(R.dimen.user_mapview_height).toPx.toInt()
        mapUtil = MapsUtil(googleMap, context, resources.displayMetrics.widthPixels, heightpx)
        mapUtil.setDefaultSettings()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}