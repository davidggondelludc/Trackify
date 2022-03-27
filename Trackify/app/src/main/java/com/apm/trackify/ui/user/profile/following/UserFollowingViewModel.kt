package com.apm.trackify.ui.user.profile.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apm.trackify.model.MockProvider
import com.apm.trackify.model.domain.User

class UserFollowingViewModel : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    init {
        users.value = MockProvider.users
    }

    fun getUsers(): LiveData<List<User>> = users
}