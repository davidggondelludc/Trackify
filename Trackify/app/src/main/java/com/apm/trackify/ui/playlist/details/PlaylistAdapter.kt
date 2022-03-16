package com.apm.trackify.ui.playlist.details

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.adapter.TouchAdapter
import com.apm.trackify.base.extensions.swap

class PlaylistAdapter(
    dataSet: MutableList<BaseModel>
) : TouchAdapter<BaseModel>(dataSet, ItemTouchHelper.RIGHT) {

    override fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean =
        viewHolder.itemViewType == R.layout.playlists_details_track_item

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