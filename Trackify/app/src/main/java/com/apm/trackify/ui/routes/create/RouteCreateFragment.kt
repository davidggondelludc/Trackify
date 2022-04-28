package com.apm.trackify.ui.routes.create

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
import com.apm.trackify.databinding.RoutesCreateFragmentBinding
import com.apm.trackify.ui.routes.create.view.adapter.PlaylistRoutesAdapter
import com.apm.trackify.ui.routes.create.view.model.RoutesCreateViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toPx
import com.apm.trackify.util.extension.toast
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class RouteCreateFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: RoutesCreateViewModel by viewModels()
    private lateinit var mapUtil: MapsUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RoutesCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RoutesCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.createRoute -> {
                    val navController = findNavController()
                    navController.navigateUp()
                }
            }
            true
        }
        binding.pinButton.setOnClickListener { it.context.toast("Set starting coordinates") }
        binding.imgBtnEndRoute.setOnClickListener { it.context.toast("Set ending coordinates") }

        binding.mapView.getFragment<SupportMapFragment>().getMapAsync(this)

        setupRecyclerView(binding.rvUserPlaylistsInRoute)
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val playlistRoutesAdapter = PlaylistRoutesAdapter()
        viewModel.getPlaylists().observe(viewLifecycleOwner) {
            playlistRoutesAdapter.submitList(it)
        }

        recyclerView.adapter = playlistRoutesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val heightpx = resources.getDimension(R.dimen.user_mapview_height).toPx.toInt()
        mapUtil = MapsUtil(googleMap, context, resources.displayMetrics.widthPixels, heightpx)
        mapUtil.setDefaultSettings()

        val coordinates = listOf<LatLng>(
            LatLng(43.371023, -8.405215), LatLng(43.382825, -8.410223),
            LatLng(43.365160, -8.374968), LatLng(43.364100, -8.399088),
            LatLng(43.358961, -8.401851)
        )

        mapUtil.drawRouteAndSetOnClick(coordinates)
    }

}