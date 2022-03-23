package com.apm.trackify.ui.user.profile.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.trackify.R
import com.apm.trackify.databinding.UserFollowingFragmentBinding
import com.apm.trackify.extensions.toast

class UserFollowingFragment: Fragment() {

    private lateinit var userFollowingAdapter: UserFollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = UserFollowingFragmentBinding.inflate(inflater, container, false)

        binding.btnReadUserQr.setOnClickListener { it.context.toast("Read user QR") }

        val userFollowingList = mutableListOf<UserFollowing>(
            UserFollowing("Usuario1", 7),
            UserFollowing("Usuario2", 5)
        )
        userFollowingAdapter = UserFollowingAdapter().apply { submitList(userFollowingList) }

        val userFollowingView = binding.rvUsersFollowing
        userFollowingView.adapter = userFollowingAdapter
        userFollowingView.layoutManager = LinearLayoutManager(userFollowingView.context)

        return binding.root
    }
}