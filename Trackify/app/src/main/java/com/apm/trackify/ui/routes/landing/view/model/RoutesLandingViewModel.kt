package com.apm.trackify.ui.routes.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.RouteItem

class RoutesLandingViewModel : ViewModel() {

    val routes = MutableLiveData<List<RouteItem>>()

    init {
        routes.value = MockProvider.routes
    }
}