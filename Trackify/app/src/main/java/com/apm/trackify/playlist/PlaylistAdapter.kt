package com.apm.trackify.playlist

import com.apm.trackify.base.adapter.DataBoundViewHolder
import com.apm.trackify.base.adapter.SimpleAdapter
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.databinding.ItemPlaylistFooterBinding
import com.apm.trackify.databinding.ItemPlaylistHeaderBinding
import com.apm.trackify.databinding.ItemPlaylistTrackBinding
import com.apm.trackify.playlist.model.DisplayableFooter
import com.apm.trackify.playlist.model.DisplayableHeader
import com.apm.trackify.playlist.model.DisplayableTrack

class PlaylistAdapter(data: MutableList<BaseModel>) : SimpleAdapter<BaseModel>(data) {

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
}