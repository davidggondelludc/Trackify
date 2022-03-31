package com.apm.trackify.ui.routes.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.RouteCreateFragmentBinding
import com.apm.trackify.util.extension.toast

class RouteCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RouteCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RouteCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        binding.shareButton.setOnClickListener { it.context.toast("This will be sharing soon!") }
        binding.pinButton.setOnClickListener { it.context.toast("Set starting coordinates") }
        binding.imgBtnEndRoute.setOnClickListener { it.context.toast("Set ending coordinates") }
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.profile_fragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}