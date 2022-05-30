package com.apm.trackify.ui.user.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.apm.trackify.R
import com.apm.trackify.databinding.UserLandingFragmentBinding
import com.apm.trackify.ui.user.landing.view.model.UserLandingViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toastError
import com.google.android.material.tabs.TabLayout
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

        setupToolbar(binding.toolbar)
        println(viewModel.toString())
        setupViewPager(binding)
        setupObservers(binding)
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

    private fun setupObservers(binding: UserLandingFragmentBinding) {
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