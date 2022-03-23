package com.apm.trackify.ui.playlist.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding
import com.apm.trackify.ui.playlist.details.view.FooterViewHolder
import java.util.concurrent.TimeUnit

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

    fun submit(trackCount: Int, duration: Int) {
//        val hours = TimeUnit.MILLISECONDS.toHours(duration)
//        val minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration))
//
//        R.plurals.minutes

//        if (hours == 0L) {
//            return context.resources.getQuantityString(R.plurals.minutes, minutes.toInt(), minutes.toInt())
//        } else {
//            var result = context.resources.getQuantityString(R.plurals.hours, hours.toInt(), hours.toInt()) + " "
//            result += context.resources.getQuantityString(R.plurals.minutes, minutes.toInt(), minutes.toInt())
//            return result
//        }

        this.title = "$trackCount \u00B7 $duration"
        notifyItemChanged(0)
    }
}