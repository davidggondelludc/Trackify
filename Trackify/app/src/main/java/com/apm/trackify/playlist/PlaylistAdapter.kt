package com.apm.trackify.playlist

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.adapter.TouchAdapter
import com.apm.trackify.base.extensions.swap

class PlaylistAdapter(
    dataSet: MutableList<BaseModel>
) : TouchAdapter<BaseModel>(dataSet) {

    override fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean =
        viewHolder.itemViewType == R.layout.item_playlist_track

    override fun onMove(from: Int, to: Int) {
        dataSet.swap(from, to)
        notifyItemMoved(from, to)
    }

    override fun onSwipeRight(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onSwipeLeft(position: Int) {}
}