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
    private var firebaseService = FirebaseService()

    init {
        users.value = MockProvider.users
        CoroutineScope(Dispatchers.IO).launch {
            val foundUsers = firebaseService.findFollowingUsers("usuario")

            withContext(Dispatchers.Main) {
                users.value = foundUsers
            }
        }
    }
}