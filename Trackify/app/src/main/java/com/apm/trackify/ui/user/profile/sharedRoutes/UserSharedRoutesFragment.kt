package com.apm.trackify.ui.user.profile.sharedRoutes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserSharedRoutesFragmentBinding
import com.apm.trackify.ui.user.profile.sharedRoutes.adapter.UserSharedRouteAdapter

class UserSharedRoutesFragment : Fragment() {

    private val viewModel: UserSharedRoutesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserSharedRoutesFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserSharedRoutesFragmentBinding.bind(view)

        setupRecyclerView(binding.rvSharedRouteItems)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val routeAdapter = UserSharedRouteAdapter()
        viewModel.getRoutes().observe(viewLifecycleOwner) {
            routeAdapter.submitList(it)
        }

        recyclerView.adapter = routeAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}