package com.apm.trackify.ui.routes.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesCreateFragmentBinding
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toast

class RouteCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RoutesCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RoutesCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.createRoute -> {
                    val navController = findNavController()
                    navController.navigateUp()
                }
            }
            true
        }
        binding.pinButton.setOnClickListener { it.context.toast("Set starting coordinates") }
        binding.imgBtnEndRoute.setOnClickListener { it.context.toast("Set ending coordinates") }
    }
}