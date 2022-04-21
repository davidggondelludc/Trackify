package com.apm.trackify.ui.user.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.service.FirebaseService
import com.apm.trackify.util.extension.setupToolbar
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserLandingFragment : Fragment() {

    private var firebaseService = FirebaseService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserLandingFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        val widthDp = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        //if (widthDp < 600) {
        setupViewPager(binding)
        //}
        setupUserName(binding,"usuario")

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
        CoroutineScope(Dispatchers.IO).launch {
            val foundUser = firebaseService.getUser(userName)

            withContext(Dispatchers.Main) {
                binding.userName.text = foundUser.userName
                binding.userFollowers.text = "${foundUser.followers.toString()} Followers"
            }
        }
    }
}