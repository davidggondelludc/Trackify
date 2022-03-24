package com.apm.trackify.ui.playlist.details.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding
import com.apm.trackify.extensions.getQuantityString
import java.util.concurrent.TimeUnit

class FooterViewHolder(private val binding: PlaylistsDetailsFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(trackCount: Int, milliseconds: Long) {
        if (trackCount >= 0) {
            val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
            val minutes =
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)

            if (hours == 0L) {
                binding.title.text = "${
                    getQuantityString(
                        R.plurals.tracks,
                        trackCount
                    )
                } · ${
                    getQuantityString(
                        R.plurals.minutes,
                        hours.toInt()
                    )
                }"
            } else {
                binding.title.text = "${
                    getQuantityString(
                        R.plurals.tracks,
                        trackCount
                    )
                } · ${
                    getQuantityString(
                        R.plurals.minutes,
                        hours.toInt()
                    )
                } ${
                    getQuantityString(R.plurals.hours, minutes.toInt())
                }"
            }
        }
    }
}