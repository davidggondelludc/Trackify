package com.apm.trackify.ui.routes.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.databinding.RoutesLandingFragmentBinding
import com.apm.trackify.databinding.RoutesShareFragmentBinding
import com.apm.trackify.extensions.toast

class ShareFragment : Fragment() {

    //private lateinit var navc: NavController
    private var _binding: RoutesShareFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RoutesShareFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shareButton.setOnClickListener{
            val toast = Toast.makeText(context, "This will be sharing soon!", Toast.LENGTH_SHORT)
            toast.show()
        }
        binding.pinButton.setOnClickListener { it.context.toast("Set starting coordinates") }
        binding.imgBtnEndRoute.setOnClickListener { it.context.toast("Set ending coordinates") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}