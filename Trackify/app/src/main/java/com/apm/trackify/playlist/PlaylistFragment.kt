package com.apm.trackify.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.drag.TouchListener
import com.apm.trackify.base.drag.ITouchListener
import com.apm.trackify.base.drag.TouchAdapter
import com.apm.trackify.base.drag.TouchHelperAdapterCallback

class PlaylistFragment : Fragment(), ITouchListener by TouchListener() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.playlist)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = PlaylistAdapter(
            PlaylistProvider.playlist.toMutableList(), this
        )
        setupListener(recyclerView)

        return view
    }
}