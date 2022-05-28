package com.apm.trackify.ui.routes.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class RoutesLandingViewModel : ViewModel() {

    val routes = MutableLiveData<List<RouteItem>>()
    var mutableRoutes = mutableListOf<RouteItem>()
    private var firebaseService = FirebaseService()

    init {
        mutableRoutes.clear()
        firebaseService.findRoutesByUserCoord() {
            mutableRoutes.add(it)
            routes.value = mutableRoutes.toList()
        }
    }
}