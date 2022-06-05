package com.apm.trackify.ui.user.landing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.R
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.ui.login.LoginActivity
import com.apm.trackify.ui.main.MainApplication
import com.apm.trackify.ui.user.landing.following.UserFollowingFragment
import com.apm.trackify.ui.user.landing.qr.QRBottomSheet
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment
import com.apm.trackify.ui.user.landing.view.model.UserLandingViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toastError
import com.apm.trackify.util.extension.toggleVisibility
import com.google.android.material.tabs.TabLayout
import com.spotify.sdk.android.auth.AuthorizationClient
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserLandingFragment : Fragment() {

    private val viewModel: UserLandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserLandingFragmentBinding.bind(view)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.qr -> {
                    if (viewModel.userId != null) {
                        val bottomSheet = QRBottomSheet().apply {
                            arguments = Bundle().apply {
                                putString("user", viewModel.userId.value)
                            }
                        }
                        bottomSheet.show(requireActivity().supportFragmentManager, "QR")
                    }
                }
                R.id.signOff -> {
                    MainApplication.TOKEN = null
                    AuthorizationClient.clearCookies(requireContext())
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                    binding.loading?.toggleVisibility(visible = true, gone = false)
                    binding.toolbar.toggleVisibility(visible = false, gone = true)
                    binding.container?.toggleVisibility(visible = false, gone = false)
                }
            }
            true
        }
        setupToolbar(binding.toolbar)
        setupObservers(binding)
    }

    private fun setUpFragments(userId: String) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val sharedRoutesFragment = UserSharedRoutesFragment.newInstance(userId, "users", false)
        val userFollowingFragment = UserFollowingFragment.newInstance(userId)
        fragmentTransaction.add(R.id.userSharedRoutesFragmentTablet, sharedRoutesFragment)
        fragmentTransaction.add(R.id.userFollowingFragmentTablet, userFollowingFragment)
        fragmentTransaction.commit()
    }

    private fun setupViewPager(binding: UserLandingFragmentBinding) {
        val adapter =
            TabLayoutPagerAdapter(this, binding.tabLayout?.tabCount ?: 0, viewModel.userId)

        binding.viewPager2?.adapter = adapter
        binding.viewPager2?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                binding.tabLayout?.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setupObservers(binding: UserLandingFragmentBinding) {
        viewModel.userId.observe(viewLifecycleOwner) {
            val tabletSize = resources.getBoolean(R.bool.isTablet)
            if (tabletSize) {
                setUpFragments(it)
            } else {
                setupViewPager(binding)
            }
        }
        viewModel.userName.observe(viewLifecycleOwner) {
            binding.userName.text = it.toString()
        }
        viewModel.userFollowers.observe(viewLifecycleOwner) {
            binding.userFollowers.text =
                resources.getQuantityString(R.plurals.followers, it.toInt(), it.toInt())
        }
        viewModel.error.observe(viewLifecycleOwner) {
            context?.toastError(it)
        }
    }
}