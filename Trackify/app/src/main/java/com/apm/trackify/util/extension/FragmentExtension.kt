package com.apm.trackify.util.extension

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

fun Fragment.setupToolbar(toolbar: Toolbar) {
    val navController = findNavController()
    val appBarConfiguration = AppBarConfiguration(
        setOf(
            com.apm.trackify.R.id.playlists_fragment,
            com.apm.trackify.R.id.routes_fragment,
            com.apm.trackify.R.id.user_fragment
        )
    )

    toolbar.setupWithNavController(navController, appBarConfiguration)
}