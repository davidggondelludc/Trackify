package com.apm.trackify.ui.user.landing

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apm.trackify.ui.user.landing.following.UserFollowingFragment
import com.apm.trackify.ui.user.landing.sharedRoutes.UserSharedRoutesFragment

class TabLayoutPagerAdapter(
    fragment: Fragment,
    private val tabCount: Int,
    userName: MutableLiveData<String>
) :
    FragmentStateAdapter(fragment) {

    private val myUserName = userName

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        val userName = myUserName.value ?: "usuario"
        return when (position) {
            0 -> UserSharedRoutesFragment.newInstance(userName)
            1 -> UserFollowingFragment.newInstance(userName)
            else -> UserSharedRoutesFragment.newInstance(userName)
        }
    }
}