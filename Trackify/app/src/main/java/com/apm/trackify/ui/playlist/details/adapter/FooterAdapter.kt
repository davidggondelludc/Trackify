package com.apm.trackify.ui.playlist.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding
import com.apm.trackify.ui.playlist.details.view.FooterViewHolder

class FooterAdapter : RecyclerView.Adapter<FooterViewHolder>() {

    private var title: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsDetailsFooterItemBinding.inflate(inflater, parent, false)

        return FooterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind(title)
    }

    override fun getItemCount(): Int = 1

    fun submit(info: String) {
        this.title = info
        notifyItemChanged(0)
    }
}