package com.apm.trackify.ui.login.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val spotifyApi: SpotifyApi
) : ViewModel() {

    private val firebaseService = FirebaseService()

    fun checkUserCreated(onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            spotifyApi.getMeUser().onSuccess {
                firebaseService.createNewUser(it.id, {
                    onSuccess()
                }, {
                    onFailure()
                })
            }.onFailure {
                onFailure()
            }
        }
    }
}