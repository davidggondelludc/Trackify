package com.apm.trackify.util.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.apm.trackify.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class MapsUtil(var map: GoogleMap, val context: Context?) : GoogleMap.OnMarkerClickListener {
    private val offset = 0.003
    private val markers = emptyList<LatLng>()
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

    fun setDefaultSettings() {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.setAllGesturesEnabled(false)
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = true
        map.setOnMarkerClickListener(this)
    }

    fun drawRoute(coordinates: List<LatLng>) {
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
                if (index == coordinates.size -1 && index != 0) {
                    createMarker(it, context,"finish")
                } else {
                    createMarker(it, context,icons[index])
                }

            }
        }
        val myBounds = LatLngBounds(
            LatLng(minLat - offset, minLong - offset),
            LatLng(maxLat + 2 * offset, maxLong + offset)
        )
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(myBounds, 5))
    }

    private fun resizeMapIcons(
        iconName: String,
        width: Int,
        height: Int,
        context: Context
    ): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(
            context.resources,
            context.resources.getIdentifier(iconName, "drawable", context.packageName)
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
        return true
    }
}
