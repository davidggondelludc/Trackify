package com.apm.trackify.playlist

import android.widget.TextView
import com.apm.trackify.R
import com.apm.trackify.model.Track

class PlaylistAdapter(data: MutableList<Track>) : SimpleAdapter<Track>(data) {
    override fun getItemViewType(position: Int): Int = R.layout.item_track

    override fun bind(holder: DataBoundViewHolder, item: Track, position: Int) {
        holder.itemView.apply {
            val title = holder.itemView.findViewById<TextView>(R.id.title)
            title.text = item.title

            val subtitle = holder.itemView.findViewById<TextView>(R.id.subtitle)
            subtitle.text = item.subtitle
        }
    }
}