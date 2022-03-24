package com.apm.trackify.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.ui.user.profile.TabLayoutPagerAdapter
import com.google.android.material.tabs.TabLayout

class UserLandingFragment : Fragment() {

    private var _binding: UserLandingFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = UserLandingFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupTabLayout()

        return root
    }

    private fun setupTabLayout() {
        val adapter = TabLayoutPagerAdapter(this, binding.tabLayout.tabCount)
        binding.viewPager2.adapter = adapter
        binding.viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}