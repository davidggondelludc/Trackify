package com.apm.trackify.ui.user.landing.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingFragmentBinding
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.ui.user.landing.UserLandingFragmentDirections
import com.apm.trackify.ui.user.landing.following.view.adapter.UserFollowingAdapter
import com.apm.trackify.ui.user.landing.following.view.model.UserFollowingViewModel
import com.apm.trackify.util.extension.toastError
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFollowingFragment : Fragment() {

    companion object {
        fun newInstance(userId: String) = UserFollowingFragment().apply {
            arguments = Bundle().apply {
                putString("userId", userId)
            }
        }
    }

    @Inject
    lateinit var spotifyApi: SpotifyApi
    private val viewModel: UserFollowingViewModel by viewModels()
    private var showedError = false

    private val barcodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents != null) {
                val navController = findNavController()
                val action =
                    UserLandingFragmentDirections.actionUserFragmentToUserFollowingProfileFragment(
                        result.contents
                    )
                navController.navigate(action)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = UserFollowingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = UserFollowingFragmentBinding.bind(view)

        viewModel.error.value = null
        binding.btnReadUserQr.setOnClickListener {
            barcodeLauncher.launch(ScanOptions().apply {
                setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                setPrompt("")
            })
        }

        setupRecyclerView(binding.rvUsersFollowing)

        setupObservers()
        val userId = arguments?.getString("userId") ?: ""
        viewModel.findFollowingUsers(userId)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val userFollowingAdapter = UserFollowingAdapter(spotifyApi)
        viewModel.users.observe(viewLifecycleOwner) {
            userFollowingAdapter.submitList(it)
        }

        recyclerView.adapter = userFollowingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.error.observe(viewLifecycleOwner) {
            if (!showedError && viewModel.error.value != null) {
                context?.toastError(it)
                showedError = true
            }
        }
    }
}