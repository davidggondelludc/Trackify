package com.apm.trackify.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleAdapter<T : BaseModel>(
    protected val dataSet: MutableList<T>
) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = dataSet[position].type

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        item.bind(holder.itemView, position)
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

