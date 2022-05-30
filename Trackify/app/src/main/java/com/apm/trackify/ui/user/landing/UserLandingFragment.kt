package com.apm.trackify.ui.user.landing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.R
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.ui.login.LoginActivity
import com.apm.trackify.ui.main.MainApplication
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toggleVisibility
import com.google.android.material.tabs.TabLayout
import com.spotify.sdk.android.auth.AuthorizationClient

class UserLandingFragment : Fragment() {

    private val firebaseService: FirebaseService = FirebaseService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserLandingFragmentBinding.bind(view)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
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

        val widthDp = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        //if (widthDp < 600) {
        setupViewPager(binding)
        //}
        setupUserName(binding, "usuario")
    }

    private fun setupViewPager(binding: UserLandingFragmentBinding) {
        val adapter = TabLayoutPagerAdapter(this, binding.tabLayout?.tabCount ?: 0)

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

    fun setupUserName(binding: UserLandingFragmentBinding, userName: String) {
        val foundUser = firebaseService.getUser(userName) { user ->
            binding.userName.text = user.userName
            binding.userFollowers.text = "${user.followers.toString()} Followers"
        }
    }
}