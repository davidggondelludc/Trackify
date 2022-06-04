package com.apm.trackify.ui.user.landing

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apm.trackify.ui.user.landing.following.UserFollowingFragment
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment

class TabLayoutPagerAdapter(
    fragment: Fragment,
    private val tabCount: Int,
    userId: MutableLiveData<String>
) :
    FragmentStateAdapter(fragment) {

    private val myUserId = userId

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        val userId = myUserId.value ?: ""
        return when (position) {
            0 -> UserSharedRoutesFragment.newInstance(userId)
            1 -> UserFollowingFragment.newInstance(userId)
            else -> UserSharedRoutesFragment.newInstance(userId)
        }
    }
}