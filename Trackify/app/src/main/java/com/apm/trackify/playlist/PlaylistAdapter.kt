package com.apm.trackify.playlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.adapter.DataBoundViewHolder
import com.apm.trackify.base.adapter.SimpleAdapter
import com.apm.trackify.base.drag.IDragListener
import com.apm.trackify.base.drag.TouchableAdapter
import com.apm.trackify.base.extensions.setOnDragListener
import com.apm.trackify.base.extensions.swap
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.databinding.ItemPlaylistFooterBinding
import com.apm.trackify.databinding.ItemPlaylistHeaderBinding
import com.apm.trackify.databinding.ItemPlaylistTrackBinding
import com.apm.trackify.playlist.model.DisplayableFooter
import com.apm.trackify.playlist.model.DisplayableHeader
import com.apm.trackify.playlist.model.DisplayableTrack

class PlaylistAdapter(dataSet: MutableList<BaseModel>, private val dragListener: IDragListener) : SimpleAdapter<BaseModel>(dataSet), TouchableAdapter{

    private val headers by lazy { dataSet.indexOfFirst { it is DisplayableTrack } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        viewHolder.setOnDragListener(R.id.dragHandle, dragListener)

        return viewHolder
    }

    override fun bind(holder: DataBoundViewHolder, item: BaseModel, position: Int) {
        // TODO: refactor this method
        when (item) {
            is DisplayableHeader -> {
                val binding = ItemPlaylistHeaderBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                }
            }
            is DisplayableTrack -> {
                val binding = ItemPlaylistTrackBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                    binding.subtitle.text = item.subtitle
                    binding.explicit.toggleVisibility(visible = item.explicit, gone = true)
                }
            }
            is DisplayableFooter -> {
                val binding = ItemPlaylistFooterBinding.bind(holder.itemView)

                holder.itemView.apply {
                    binding.title.text = item.title
                }
            }
        }
    }

    override fun canInteractWithViewHolder(viewType: Int): Boolean {
        return viewType == R.layout.item_playlist_track
    }

    override fun onMoved(from: Int, to: Int) {
        dataSet.swap(from, to)
    }

    override fun onSwipedLeft(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun afterSwipeLeft(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onSwipedRight(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun afterSwipeRight(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onClearView() {

    }
}