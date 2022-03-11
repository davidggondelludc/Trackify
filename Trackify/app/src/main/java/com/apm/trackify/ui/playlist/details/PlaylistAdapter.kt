package com.apm.trackify.ui.playlist.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.adapter.DataBoundViewHolder
import com.apm.trackify.base.adapter.SimpleAdapter
import com.apm.trackify.base.drag.ITouchListener
import com.apm.trackify.base.drag.TouchAdapter
import com.apm.trackify.base.extensions.setOnTouchListener
import com.apm.trackify.base.extensions.swap
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.databinding.PlaylistsDetailsHeaderItemBinding
import com.apm.trackify.databinding.PlaylistsDetailsFooterItemBinding
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding
import com.apm.trackify.ui.playlist.details.model.DisplayableFooter
import com.apm.trackify.ui.playlist.details.model.DisplayableHeader
import com.apm.trackify.ui.playlist.details.model.DisplayableTrack

class PlaylistAdapter(dataSet: MutableList<BaseModel>, private val touchListener: ITouchListener) :
    SimpleAdapter<BaseModel>(dataSet), TouchAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        viewHolder.setOnTouchListener(R.id.dragHandle, touchListener)

        return viewHolder
    }

    override fun bind(holder: DataBoundViewHolder, item: BaseModel, position: Int) {
        // TODO: refactor this method
        when (item) {
            is DisplayableHeader -> {
                val binding = PlaylistsDetailsHeaderItemBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                }
            }
            is DisplayableTrack -> {
                val binding = PlaylistsDetailsTrackItemBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                    binding.subtitle.text = item.subtitle
                    binding.explicit.toggleVisibility(item.explicit, true)
                }
            }
            is DisplayableFooter -> {
                val binding = PlaylistsDetailsFooterItemBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                }
            }
        }
    }

    override fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
        return getItem(viewHolder.adapterPosition)?.type == R.layout.playlists_details_track_item
    }

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