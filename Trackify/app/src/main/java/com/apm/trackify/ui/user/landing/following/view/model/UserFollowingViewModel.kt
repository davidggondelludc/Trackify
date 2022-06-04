package com.apm.trackify.ui.user.landing.following.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.domain.UserItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class UserFollowingViewModel : ViewModel() {

    val users = MutableLiveData<List<UserItem>>()
    var mutableUsers = mutableListOf<UserItem>()
    private var firebaseService = FirebaseService()

    fun findFollowingUsers(userId: String) {
        mutableUsers.clear()
        firebaseService.findFollowingUsers(userId, {
            mutableUsers.add(it)
            users.value = mutableUsers.toList()
        }, {})
    }
}