package com.apm.trackify.ui.user.landing.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLandingViewModel @Inject constructor(
    private val spotifyApi: SpotifyApi
) : ViewModel() {

    private var firebaseService = FirebaseService()
    val userName = MutableLiveData<String>()
    val userFollowers = MutableLiveData<Long>()
    val error = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            spotifyApi.getMeUser().onSuccess {
                firebaseService.getUser(it.id,
                    { user ->
                        userName.value = it.display_name
                        userFollowers.value = user.followers
                    }, { error.value = R.string.userNameNotFound })
            }.onFailure {
                error.value = R.string.userNameNotFound
            }
        }
    }
}