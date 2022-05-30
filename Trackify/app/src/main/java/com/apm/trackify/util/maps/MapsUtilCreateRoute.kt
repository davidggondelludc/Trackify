package com.apm.trackify.util.maps

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsUtilCreateRoute(
    private var map: GoogleMap,
    val context: Context?,
    val width: Int,
    val height: Int
) : GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {
    private val icons = listOf(
        "uno",
        "dos",
        "tres",
        "cuatro",
        "cinco",
        "seis",
        "siete",
        "ocho",
        "nueve"
    )
    private val markersCreated = mutableListOf<Marker>()

    fun setDefaultSettings() {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(true)
        map.uiSettings.isZoomControlsEnabled = true
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = false
        map.setOnMapClickListener(this)

        map.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
            }

            override fun onMarkerDrag(marker: Marker) {
            }
        })
    }

    fun getAllMarkers(): MutableList<LatLng> {
        val coordinatesList = mutableListOf<LatLng>()
        markersCreated.forEach{coordinatesList.add(it.position)}
        return coordinatesList
    }

    fun clearMarkers() {
        markersCreated.clear()
        map.clear()
    }

    private fun resizeMapIcons(
        iconName: String,
        width: Int,
        height: Int,
        context: Context
    ): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(
            context.resources,
            context.resources.getIdentifier(iconName, "raw", context.packageName)
        )
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    private fun createMarker(coordinates: LatLng, context: Context, iconName: String) {
        val marker: MarkerOptions = MarkerOptions().position(coordinates)
            .icon(resizeMapIcons(iconName, 64, 64, context)?.let {
                BitmapDescriptorFactory.fromBitmap(it)
            }).draggable(true)

        map.addMarker(marker)?.let { markersCreated.add(it) }
    }

    override fun onMapClick(coordinates: LatLng) {
        if (context != null) {
            if (markersCreated.size < 9) {
                createMarker(coordinates, context, icons[markersCreated.count()])
            } else {
                Toast.makeText(this.context, "No more markers available", Toast.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("MissingPermission")
    fun setUpMap(fusedLocationProviderClient: FusedLocationProviderClient) {
        if (context != null) {
            map.isMyLocationEnabled = true
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        return true
    }
}