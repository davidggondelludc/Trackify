package com.apm.trackify

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.playlist_song_item.view.*

class SelectSongAdapter(
    private val songs: MutableList<SelectSong>
) : RecyclerView.Adapter<SelectSongAdapter.SelectSongViewHolder>() {

    class SelectSongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSongViewHolder {
        return SelectSongViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.playlist_song_item,
                parent,
                false
            )
        )
    }

    fun addSong(song: SelectSong) {
        songs.add(song)
        notifyItemInserted(songs.size - 1)
    }

    private fun toggleStrikeThrough(tvSong: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvSong.paintFlags = tvSong.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvSong.paintFlags = tvSong.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: SelectSongViewHolder, position: Int) {
        val curSong = songs[position]
        holder.itemView.apply {
            tvSong.text = curSong.title
            cbSelectedSong.isChecked = curSong.isChecked
            toggleStrikeThrough(tvSong, curSong.isChecked)
            cbSelectedSong.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvSong, isChecked)
                curSong.isChecked = !curSong.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}