package com.apm.trackify.ui.user.landing.sharedRoutes.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.R
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class UserSharedRoutesViewModel : ViewModel() {

    val routes = MutableLiveData<List<RouteItem>>()
    val error = MutableLiveData<Int>()
    var mutableRoutes = mutableListOf<RouteItem>()

    private var firebaseService = FirebaseService()

    fun findRoutes(userName: String) {
        mutableRoutes.clear()
        routes.value = emptyList()
        firebaseService.findRoutesByUsername(userName, {
            mutableRoutes.add(it)
            routes.value = mutableRoutes.toList()
        }, {
            error.value = R.string.couldNotFindRoutes
            routes.value = emptyList()
        })
    }
}