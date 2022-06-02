package com.apm.trackify.ui.user.followingProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.apm.trackify.R
import com.apm.trackify.databinding.UserFollowingProfileFragmentBinding
import com.apm.trackify.ui.user.followingProfile.view.model.FollowingProfileViewModel
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment
import com.apm.trackify.util.extension.toastError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingProfileFragment : Fragment() {

    private val args: FollowingProfileFragmentArgs by navArgs()
    private val viewModel: FollowingProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserFollowingProfileFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserFollowingProfileFragmentBinding.bind(view)

        val fragmentTransaction = childFragmentManager.beginTransaction()
        val sharedRoutesFragment = UserSharedRoutesFragment.newInstance(args.userId)
        fragmentTransaction.add(R.id.sharedRoutesProfileFragmentContainer, sharedRoutesFragment)
        fragmentTransaction.commit()

        binding.userName.text = args.userId

        setupObservers(binding)
        viewModel.findUserName(args.userId)
    }

    private fun setupObservers(binding: UserFollowingProfileFragmentBinding) {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.userName.text = it.display_name
        }
        viewModel.followed.observe(viewLifecycleOwner) {
            if (!it) {
                binding.followButton.text = getString(R.string.follow)
                binding.followButton.setOnClickListener {
                    viewModel.follow(args.userId)
                }
            } else {
                binding.followButton.text = getString(R.string.unfollow)
                binding.followButton.setOnClickListener {
                    viewModel.unfollow(args.userId)
                }
            }
        }
        viewModel.userFollowers.observe(viewLifecycleOwner) {
            binding.userFollowers.text = resources.getQuantityString(
                R.plurals.followers,
                it,
                it
            )
        }
        viewModel.error.observe(viewLifecycleOwner) {
            context?.toastError(it)
        }
    }
}