package com.apm.trackify.ui.routes.landing.view.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class RoutesLandingViewModel (latitude: Double, longitude: Double) : ViewModel() {

    var routes = MutableLiveData<List<RouteItem>>()
    var mutableRoutes = mutableListOf<RouteItem>()
    private var firebaseService = FirebaseService()

    init {
        Log.d("LAT", latitude.toString())
        Log.d("LONG", longitude.toString())
        mutableRoutes.clear()
        firebaseService.findRoutesByUserCoord(latitude, longitude) {
            mutableRoutes.add(it)
            routes.value = mutableRoutes.toList()
        }
    }
}