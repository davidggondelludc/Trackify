package com.apm.trackify.ui.user.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutPagerAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when (position)
        {
            0 -> UserSharedRoutesFragment()
            1 -> UserFollowingFragment()
            else -> UserSharedRoutesFragment()
        }
    }
}