package com.apm.trackify.ui.user.followingProfile.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.provider.service.spotify.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingProfileViewModel @Inject constructor(
    private val spotifyApi: SpotifyApi
) : ViewModel() {

    val firebaseService = FirebaseService()
    val user = MutableLiveData<User>()
    val followed = MutableLiveData<Boolean>()
    val userFollowers = MutableLiveData<Int>()
    val error = MutableLiveData<Int>()

    fun findUserName(userId: String) {
        viewModelScope.launch {
            spotifyApi.getUserById(userId).onSuccess {
                user.value = it
            }.onFailure {
                error.value = R.string.userNameNotFound
            }
            spotifyApi.getMeUser().onSuccess {
                firebaseService.checkFollowed(it.id, userId) {
                    followed.value = it
                }
            }
        }
        findFollowers(userId)
    }

    fun findFollowers(userId: String) {
        firebaseService.getUser(userId, {
            userFollowers.value = it.followers.toInt()
        }, {
            error.value = R.string.followersNotFound
        })
    }

    fun follow(userId: String) {
        viewModelScope.launch {
            spotifyApi.getMeUser().onSuccess {
                firebaseService.follow(it.id, userId, {
                    followed.value = true
                    findFollowers(userId)
                }, {
                    error.value = R.string.couldNotFollow
                })
            }.onFailure {
                error.value = R.string.couldNotFollow
            }
        }
    }

    fun unfollow(userId: String) {
        viewModelScope.launch {
            spotifyApi.getMeUser().onSuccess {
                firebaseService.unfollow(it.id, userId, {
                    followed.value = false
                    findFollowers(userId)
                }, {
                    error.value = R.string.couldNotUnfollow
                })
            }.onFailure {
                error.value = R.string.couldNotUnfollow
            }
        }
    }
}