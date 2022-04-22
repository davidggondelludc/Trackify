package com.apm.trackify.ui.user.landing.sharedRoutes.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Route
import com.apm.trackify.service.firebase.FirebaseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSharedRoutesViewModel : ViewModel() {

    val routes = MutableLiveData<List<Route>>()
    private var firebaseService = FirebaseService()

    init {
        routes.value = MockProvider.routes
        CoroutineScope(Dispatchers.IO).launch {
            val foundRoutes = firebaseService.findRoutesByUsername("usuario")

            withContext(Dispatchers.Main) {
                routes.value = foundRoutes
            }
        }
    }
}