package com.apm.trackify.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.base.extensions.isInBounds

abstract class SimpleAdapter<T : BaseModel>(protected val dataSet: MutableList<T>) :
    RecyclerView.Adapter<DataBoundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)

        return DataBoundViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = dataSet[position].type

    fun getItem(position: Int): T? {
        return if (dataSet.isInBounds(position)) {
            dataSet[position]
        } else {
            null
        }
    }
    
    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        val item = dataSet[position]
        bind(holder, item, position)
    }

    protected abstract fun bind(holder: DataBoundViewHolder, item: T, position: Int)

    override fun getItemCount(): Int = dataSet.size
}