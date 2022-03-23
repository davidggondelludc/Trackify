package com.apm.trackify.ui.routes.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.databinding.RoutesSearchFragmentBinding

class SearchFragment : Fragment() {

    private lateinit var navc: NavController
    private var _binding: RoutesSearchFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = RoutesSearchFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)
        binding.btnAddPlaylist2route.setOnClickListener {
            navc.navigate(R.id.action_routes_search_to_route_share)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}