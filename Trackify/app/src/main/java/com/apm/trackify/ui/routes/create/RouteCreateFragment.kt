package com.apm.trackify.ui.routes.create

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesCreateFragmentBinding
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.ui.routes.create.view.adapter.PlaylistRoutesAdapter
import com.apm.trackify.ui.routes.create.view.model.RoutesCreateViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toPx
import com.apm.trackify.util.extension.toastError
import com.apm.trackify.util.extension.toastSuccess
import com.apm.trackify.util.maps.MapsUtilCreateRoute
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteCreateFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: RoutesCreateViewModel by viewModels()
    private lateinit var mapUtil: MapsUtilCreateRoute
    private var firebaseService: FirebaseService = FirebaseService()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var numberPlaylistSelected: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityResultContracts.RequestMultiplePermissions()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RoutesCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RoutesCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            val userName = viewModel.user.value?.id ?: ""
            val playlistRoutesAdapter: PlaylistRoutesAdapter =
                binding.rvUserPlaylistsInRoute.adapter as PlaylistRoutesAdapter

            numberPlaylistSelected = playlistRoutesAdapter.getSelectedPosition()

            if (userName.isNotEmpty()
                && mapUtil.getAllMarkers().size > 1
                && numberPlaylistSelected != -1
                && binding.routeName.text.toString().isNotEmpty())
            {
                val routeName: String = binding.routeName.text.toString().trim()
                when (it.itemId) {
                    R.id.createRoute -> {
                        firebaseService.createNewRoute(
                            userName,
                            routeName,
                            mapUtil.getAllMarkers(),
                            getUrlPlaylist(numberPlaylistSelected).split("/").last(),
                            {},
                            {})
                        val navController = findNavController()
                        navController.navigateUp()
                        context?.toastSuccess(R.string.create_route_success)
                    }
                }
            } else if (mapUtil.getAllMarkers().size <= 1) {
                context?.toastError(R.string.create_route_markers_error)
            } else if (binding.routeName.text.toString().isEmpty()) {
                context?.toastError(R.string.create_route_name_error)
            } else if (numberPlaylistSelected == -1) {
                context?.toastError(R.string.create_route_playlist_selection_error)
            } else {
                context?.toastError(R.string.create_route_unknown_error)
            }
            true
        }
        binding.deleteButton.setOnClickListener { mapUtil.clearMarkers() }
        binding.mapView.getFragment<SupportMapFragment>().getMapAsync(this)
        setupRecyclerView(binding.rvUserPlaylistsInRoute)
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val playlistRoutesAdapter = PlaylistRoutesAdapter()

        viewModel.error.observe(viewLifecycleOwner) {
            context?.toastError(it)
        }

        viewModel.playlists.observe(viewLifecycleOwner) {
            playlistRoutesAdapter.submitList(it)
        }

        recyclerView.adapter = playlistRoutesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful) {
                    val heightpx = resources.getDimension(R.dimen.user_mapview_height).toPx.toInt()
                    mapUtil =
                        MapsUtilCreateRoute(
                            googleMap,
                            context,
                            resources.displayMetrics.widthPixels,
                            heightpx
                        )
                    mapUtil.setDefaultSettings()

                    mapUtil.setUpMap(fusedLocationClient)
                } else {
                    Log.w(ContentValues.TAG, "getLastLocation:exception", taskLocation.exception)
                    context?.toastError(R.string.create_route_location_error)
                    val navController = findNavController()
                    navController.navigateUp()
                }
            }
    }

    private fun getUrlPlaylist(position: Int): String {
        return viewModel.playlists.value?.get(position)?.playlistUri ?: ""
    }
}