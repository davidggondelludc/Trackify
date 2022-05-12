package com.apm.trackify.ui.user.landing.sharedRoutes.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class UserSharedRoutesViewModel : ViewModel() {

    val routes = MutableLiveData<List<RouteItem>>()
    var mutableRoutes = mutableListOf<RouteItem>()
    private var firebaseService = FirebaseService()

    init {
        mutableRoutes.clear()
        firebaseService.findRoutesByUsername("usuario") {
            mutableRoutes.add(it)
            routes.value = mutableRoutes
        }
    }
}