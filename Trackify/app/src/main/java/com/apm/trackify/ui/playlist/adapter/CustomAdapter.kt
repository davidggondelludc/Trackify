package com.apm.trackify.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistCoverItemBinding
import com.apm.trackify.ui.playlist.model.PlaylistCoverItems

class CustomAdapter(private val mList: List<PlaylistCoverItems>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(PlaylistCoverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(itemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.text

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(binding: PlaylistCoverItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageView: ImageView = itemView.findViewById(R.id.imageCover)
        val textView: TextView = itemView.findViewById(R.id.textCoverItem)

        private lateinit var navc: NavController

        init {
            binding.root.setOnClickListener {
                navc = Navigation.findNavController(binding.root)
                navc.navigate(R.id.action_navigation_landing_to_navigation_details)
            }
        }

    }
}
