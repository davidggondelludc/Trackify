package com.apm.trackify.ui.user.landing.sharedRoutes.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Route

class UserSharedRoutesViewModel : ViewModel() {

    val routes = MutableLiveData<List<Route>>()

    init {
        routes.value = MockProvider.routes
    }
}