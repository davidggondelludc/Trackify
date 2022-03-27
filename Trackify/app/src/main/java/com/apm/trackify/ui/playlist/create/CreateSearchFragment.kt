package com.apm.trackify.ui.playlist.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding

class CreateSearchFragment : Fragment() {
    private lateinit var binding: PlaylistsCreateSearchFragmentBinding

    private lateinit var navc: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}