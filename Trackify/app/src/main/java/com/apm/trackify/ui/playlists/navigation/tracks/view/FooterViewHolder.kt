package com.apm.trackify.ui.playlists.navigation.tracks.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistTrackFooterItemBinding
import com.apm.trackify.extensions.getQuantityString
import java.util.concurrent.TimeUnit

class FooterViewHolder(private val binding: PlaylistTrackFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(trackCount: Int, milliseconds: Long) {
        if (trackCount >= 0) {
            val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
            val minutes =
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)

            when {
                hours == 0L -> {
                    binding.title.text = "${
                        getQuantityString(R.plurals.tracks, trackCount, trackCount)
                    } · ${
                        getQuantityString(R.plurals.minutes, minutes.toInt(), minutes.toInt())
                    }"
                }
                minutes == 0L -> {
                    binding.title.text = "${
                        getQuantityString(R.plurals.tracks, trackCount, trackCount)
                    } · ${
                        getQuantityString(R.plurals.hours, hours.toInt(), hours.toInt())
                    }"
                }
                else -> {
                    binding.title.text = "${
                        getQuantityString(R.plurals.tracks, trackCount, trackCount)
                    } · ${
                        getQuantityString(R.plurals.hours, hours.toInt(), hours.toInt())
                    } ${
                        getQuantityString(R.plurals.minutes, minutes.toInt(), minutes.toInt())
                    }"
                }
            }
        }
    }
}