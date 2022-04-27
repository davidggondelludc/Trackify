package com.apm.trackify.util.maps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import java.util.jar.Manifest

class MapsUtilCreateRoute(var map: GoogleMap, val context: Context?, val width: Int, val height: Int) : GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {
    private val offset = 0.003
    private lateinit var mapsRouteUrl: String
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
    private val coordinatesCreated = mutableListOf<LatLng>();

    fun setDefaultSettings() {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(true)
        map.uiSettings.isZoomControlsEnabled = true
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = false
        map.setOnMarkerClickListener(this)
    }

    fun getAllClicks (): MutableList<LatLng> {
        return coordinatesCreated
    }

    fun clearClicks () {
        coordinatesCreated.clear()
        map.clear();
    }

    fun drawRouteAndSetOnClick() {
/*        if (coordinates.size >= 2) {
            drawRoute(coordinates)
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
            mapsRouteUrl = auxUrl
        }*/




        map.setOnMapClickListener(this)

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
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(myBounds, (width * 0.5).toInt(), (height * 0.5).toInt(), 10))
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
        Toast.makeText(this.context, coordinates.toString(), Toast.LENGTH_SHORT).show()
        if (context != null) {
            coordinatesCreated.add(coordinates)
            createMarker(coordinates, context, icons[coordinatesCreated.count()-1])
        }

    }

    fun setUpMap(context: Context, fusedLocationProviderClient: FusedLocationProviderClient) {

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1000)
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1001)
        }
        map.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

    }
}