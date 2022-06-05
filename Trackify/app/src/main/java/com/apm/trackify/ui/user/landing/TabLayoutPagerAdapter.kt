package com.apm.trackify.ui.user.landing

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apm.trackify.ui.user.landing.following.UserFollowingFragment
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment

class TabLayoutPagerAdapter(
    fragment: Fragment,
    private val tabCount: Int,
    private val userId: MutableLiveData<String>
) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        val userId = userId.value ?: ""
        return when (position) {
            0 -> UserSharedRoutesFragment.newInstance(userId, "users", true)
            1 -> UserFollowingFragment.newInstance(userId)
            else -> UserSharedRoutesFragment.newInstance(userId, "users", true)
        }
    }
}