package com.apm.trackify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.playlist.PlaylistAdapter
import com.apm.trackify.playlist.PlaylistProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_playlist)

        val recyclerView = findViewById<RecyclerView>(R.id.playlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlaylistAdapter(PlaylistProvider.playlist.toMutableList())
    }
}