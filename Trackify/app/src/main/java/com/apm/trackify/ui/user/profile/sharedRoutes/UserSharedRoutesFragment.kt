package com.apm.trackify.ui.user.profile.sharedRoutes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.UserSharedRoutesFragmentBinding

class UserSharedRoutesFragment: Fragment() {

    private lateinit var sharedRouteAdapter: SharedRouteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = UserSharedRoutesFragmentBinding.inflate(inflater, container, false)

        val sharedRouteList = mutableListOf(SharedRoute("Playlist 1", 3), SharedRoute("Playlist 2", 4))
        sharedRouteAdapter = SharedRouteAdapter().apply { submitList(sharedRouteList) }

        val sharedRoutes = binding.rvSharedRouteItems
        sharedRoutes.adapter = sharedRouteAdapter
        sharedRoutes.layoutManager = LinearLayoutManager(sharedRoutes.context)

        return binding.root
    }


}