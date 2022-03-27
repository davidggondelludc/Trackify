package com.apm.trackify.ui.user.profile.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingFragmentBinding
import com.apm.trackify.ui.user.profile.following.adapter.UserFollowingAdapter
import com.apm.trackify.util.extension.toast

class UserFollowingFragment : Fragment() {

    private val viewModel: UserFollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = UserFollowingFragmentBinding.inflate(inflater, container, false)

        binding.btnReadUserQr.setOnClickListener { it.context.toast("Read user QR") }

        setUpRecyclerView(binding.rvUsersFollowing)

        return binding.root
    }

    fun setUpRecyclerView(recyclerView: RecyclerView) {
        val userFollowingAdapter = UserFollowingAdapter()

        viewModel.getUsers().observe(viewLifecycleOwner) {
            userFollowingAdapter.submitList(it)
        }

        recyclerView.adapter = userFollowingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}