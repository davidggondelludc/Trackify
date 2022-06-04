package com.apm.trackify.ui.routes.landing.view.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.repository.SpotifyRepository
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RoutesLandingViewModel @Inject constructor(
    spotifyApi: SpotifyApi, latitude: Double, longitude: Double
) : ViewModel() {


    var routes = MutableLiveData<List<RouteItem>>()
    var mutableRoutes = mutableListOf<RouteItem>()
    private var firebaseService = FirebaseService()
    val user = MutableLiveData<User>()
    val error = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            try {
                user.value = spotifyApi.getMeUser().getOrNull()
                Log.d("USUARIO", user.value.toString())
                mutableRoutes.clear()
                firebaseService.findRoutesByUserCoord(user.value?.id?: "", latitude, longitude) {
                    mutableRoutes.add(it)
                    routes.value = mutableRoutes.toList()
                }
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }
}