package com.apm.trackify.ui.user.landing.following.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.User
import com.apm.trackify.service.firebase.FirebaseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserFollowingViewModel : ViewModel() {

    val users = MutableLiveData<List<User>>()
    var mutableUsers = mutableListOf<User>()
    private var firebaseService = FirebaseService()

    init {
        mutableUsers.clear()
        firebaseService.findFollowingUsers("usuario") {
            mutableUsers.add(it)
            users.value = mutableUsers.toList()
        }
    }
}