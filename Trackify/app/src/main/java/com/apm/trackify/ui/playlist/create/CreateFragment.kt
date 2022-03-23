package com.apm.trackify.ui.playlist.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding

class CreateFragment : Fragment() {

    private lateinit var binding: PlaylistsCreateFragmentBinding

    private lateinit var navc: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlaylistsCreateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)
        binding.returnButton.setOnClickListener { navc.navigate(R.id.action_navigation_create_to_navigation_landing) }
        binding.saveButton.setOnClickListener {
            Toast.makeText(activity, "Saved!", Toast.LENGTH_LONG).show()
            navc.navigate(R.id.action_navigation_create_to_navigation_landing)
        }
        binding.formSearchButton.setOnClickListener { Toast.makeText(activity, "Showing results", Toast.LENGTH_SHORT).show() }
    }
}