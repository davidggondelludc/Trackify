package com.apm.trackify.ui.routes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.ui.routes.model.PlaylistRoutesItemsViewModel

class PlaylistRoutesAdapter(private val mList: List<PlaylistRoutesItemsViewModel>) : RecyclerView.Adapter<PlaylistRoutesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RoutesPlaylistsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.playlist.text = itemsViewModel.playlist

        // sets the text to the textview from our itemHolder class
        holder.user.text = itemsViewModel.user

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(binding: RoutesPlaylistsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val playlist: TextView = binding.tvPlaylistInRoute
        val user: TextView = binding.tvUserPlaylistsInRoute

        private lateinit var navc: NavController

        init {
            binding.imgBtnSeePlayList.setOnClickListener {
                navc = Navigation.findNavController(binding.root)
                navc.navigate(R.id.action_routes_search_to_playlist_details)
            }
        }

    }
}
