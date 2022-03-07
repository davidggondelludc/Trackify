package com.apm.trackify.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleAdapter<T>(private val dataSet: MutableList<T>) :
    RecyclerView.Adapter<DataBoundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)

        return DataBoundViewHolder(view)
    }

    abstract override fun getItemViewType(position: Int): Int

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        val item = dataSet[position]
        bind(holder, item, position)
    }

    protected abstract fun bind(holder: DataBoundViewHolder, item: T, position: Int)

    override fun getItemCount(): Int = dataSet.size
}