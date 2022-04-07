package com.apm.trackify.ui.user.profile.sharedRoutes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.Route

class UserSharedRoutesViewModel : ViewModel() {

    private val routes = MutableLiveData<List<Route>>()

    init {
        routes.value = MockProvider.routes
    }

    fun getRoutes(): LiveData<List<Route>> = routes
}