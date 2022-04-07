package com.apm.trackify.ui.user.landing

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apm.trackify.ui.user.landing.following.UserFollowingFragment
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment

class TabLayoutPagerAdapter(fragment: Fragment, private val tabCount: Int) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserSharedRoutesFragment()
            1 -> UserFollowingFragment()
            else -> UserSharedRoutesFragment()
        }
    }
}