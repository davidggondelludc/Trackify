package com.apm.trackify.ui.user.landing.following.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.provider.model.domain.UserItem
import com.apm.trackify.provider.service.firebase.FirebaseService

class UserFollowingViewModel : ViewModel() {

    val users = MutableLiveData<List<UserItem>>()
    var mutableUsers = mutableListOf<UserItem>()
    private var firebaseService = FirebaseService()

    init {
        mutableUsers.clear()
        firebaseService.findFollowingUsers("usuario") {
            mutableUsers.add(it)
            users.value = mutableUsers.toList()
        }
    }
}