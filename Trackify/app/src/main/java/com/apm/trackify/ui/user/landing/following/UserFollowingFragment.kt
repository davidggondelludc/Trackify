package com.apm.trackify.ui.user.landing.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingFragmentBinding
import com.apm.trackify.ui.user.landing.following.view.adapter.UserFollowingAdapter
import com.apm.trackify.ui.user.landing.following.view.model.UserFollowingViewModel
import com.apm.trackify.util.extension.toastError

class UserFollowingFragment : Fragment() {

    private val viewModel: UserFollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserFollowingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserFollowingFragmentBinding.bind(view)

        binding.btnReadUserQr.setOnClickListener {
            it.context.toastError("Read user QR")
        }

        setupRecyclerView(binding.rvUsersFollowing)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val userFollowingAdapter = UserFollowingAdapter()
        viewModel.users.observe(viewLifecycleOwner) {
            userFollowingAdapter.submitList(it)
        }

        recyclerView.adapter = userFollowingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}