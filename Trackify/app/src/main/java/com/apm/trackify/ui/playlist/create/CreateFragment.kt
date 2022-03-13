package com.apm.trackify.ui.playlist.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding

class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.playlists_create_fragment, container, false)
    }
}