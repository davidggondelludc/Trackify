package com.apm.trackify.ui.user.landing.sharedRoutes.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.RouteItem
import com.apm.trackify.service.firebase.FirebaseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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