package com.apm.trackify.util.maps

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class MapsUtil(var map: GoogleMap, val context: Context?, val width: Int, val height: Int) :
    GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {

    private val offset = 0.003
    private var mapsRouteUrl = ""
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

    companion object {
        fun getRouteURL(coordinates: List<LatLng>): String {
            if (coordinates.size >= 2) {
                var auxUrl = "https://www.google.com/maps/dir/?api=1&"
                auxUrl += "origin=" + coordinates[0].latitude + "," + coordinates[0].longitude + "&"
                val waypoints = coordinates.subList(1, coordinates.size - 1)
                auxUrl += "waypoints="
                waypoints.forEachIndexed { index, it ->
                    auxUrl += it.latitude.toString() + "," + it.longitude.toString()
                    if (index != waypoints.size - 1) {
                        auxUrl += "|"
                    }
                }
                auxUrl += "&destination=" + coordinates[coordinates.size - 1].latitude + "," + coordinates[coordinates.size - 1].longitude + "&travelmode=walking"
                return auxUrl
            }
            return ""
        }
    }

    fun setDefaultSettings() {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(false)
        map.uiSettings.isZoomControlsEnabled = false
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = false
        map.setOnMapClickListener(this)
        map.setOnMarkerClickListener(this)
    }

    fun setCustomSettings() {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(true)
        map.uiSettings.isZoomControlsEnabled = true
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = false
        map.setOnMapClickListener(this)
        map.setOnMarkerClickListener(this)
    }

    fun drawRouteAndSetOnClick(coordinates: List<LatLng>) {
        if (coordinates.size >= 2) {
            drawRoute(coordinates)
            mapsRouteUrl = getRouteURL(coordinates)
            map.setOnMapClickListener(this)
        }
    }

    fun drawRoute(coordinates: List<LatLng>) {
        if (coordinates.size > 10) {
            throw TooManyMarkersException()
        }
        map.clear()
        var minLat = Double.POSITIVE_INFINITY
        var minLong = Double.POSITIVE_INFINITY
        var maxLat = Double.NEGATIVE_INFINITY
        var maxLong = Double.NEGATIVE_INFINITY

        coordinates.forEachIndexed { index, it ->
            if (it.latitude < minLat) {
                minLat = it.latitude
            }
            if (it.longitude < minLong) {
                minLong = it.longitude
            }
            if (it.latitude > maxLat) {
                maxLat = it.latitude
            }
            if (it.longitude > maxLong) {
                maxLong = it.longitude
            }
            if (context != null) {
                if (index == coordinates.size - 1 && index != 0) {
                    createMarker(it, context, "finish")
                } else {
                    createMarker(it, context, icons[index])
                }
            }
        }

        val myBounds = LatLngBounds(
            LatLng(minLat - offset, minLong - offset),
            LatLng(maxLat + 2 * offset, maxLong + offset)
        )
        map.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                myBounds,
                (width * 0.5).toInt(),
                (height * 0.5).toInt(),
                10
            )
        )
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
        var marker: MarkerOptions = MarkerOptions().position(coordinates)
            .icon(resizeMapIcons(iconName, 64, 64, context)?.let {
                BitmapDescriptorFactory.fromBitmap(it)
            })
        map.addMarker(marker)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsRouteUrl))
        context?.startActivity(intent)
        return true
    }

    override fun onMapClick(coordinates: LatLng) {
        if (mapsRouteUrl != "") {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsRouteUrl))
            context?.startActivity(intent)
        }
    }

    @SuppressLint("MissingPermission")
    fun createUserMarker(userCoordinates: LatLng) {
        map.isMyLocationEnabled = true
        val currentLatLng = LatLng(userCoordinates.latitude, userCoordinates.longitude)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
        var zoom = ",15z"
        var auxUrl =
            "https://www.google.com/maps/place/" + userCoordinates.latitude + "," + userCoordinates.longitude + zoom
        mapsRouteUrl = auxUrl
        map.setOnMapClickListener(this)
    }
}