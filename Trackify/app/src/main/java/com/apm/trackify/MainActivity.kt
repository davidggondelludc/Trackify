package com.apm.trackify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.base.drag.DragListenerImpl
import com.apm.trackify.base.drag.IDragListener
import com.apm.trackify.base.extensions.lazyFast
import com.apm.trackify.playlist.PlaylistAdapter
import com.apm.trackify.playlist.PlaylistProvider

class MainActivity : AppCompatActivity(), IDragListener by DragListenerImpl() {

    private val adapter by lazyFast {
        PlaylistAdapter(
            PlaylistProvider.playlist.toMutableList(), this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_playlist)

        val recyclerView = findViewById<RecyclerView>(R.id.playlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        setupDragListener(recyclerView, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }
}