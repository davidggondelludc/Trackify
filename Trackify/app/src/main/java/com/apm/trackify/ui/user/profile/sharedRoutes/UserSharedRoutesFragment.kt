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
import com.apm.trackify.ui.user.profile.sharedRoutes.adapter.SharedRouteAdapter

class UserSharedRoutesFragment : Fragment() {

    private val viewModel: UserSharedRoutesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = UserSharedRoutesFragmentBinding.inflate(inflater, container, false)

        setUpRecyclerView(binding.rvSharedRouteItems)

        return binding.root
    }

    fun setUpRecyclerView(recyclerView: RecyclerView) {
        val routeAdapter = SharedRouteAdapter()
        viewModel.getRoutes().observe(viewLifecycleOwner) {
            routeAdapter.submitList(it)
        }

        recyclerView.adapter = routeAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}