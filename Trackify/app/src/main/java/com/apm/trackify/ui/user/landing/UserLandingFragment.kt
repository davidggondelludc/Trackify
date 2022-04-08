package com.apm.trackify.ui.user.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.util.extension.setupToolbar
import com.google.android.material.tabs.TabLayout

class UserLandingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserLandingFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        val widthDp = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        if (widthDp < 600) {
            setupViewPager(binding)
        }
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
}