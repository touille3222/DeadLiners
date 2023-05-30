package com.zhanuardy.deadliners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val list: ArrayList<ActivityDataClass>, val clickLambda: (Int)->Unit):RecyclerView.Adapter<Adapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Adapter.ListViewHolder, position: Int) {
        val currentItem = list[position]
        holder.tvName.text = currentItem.title
        holder.itemView.setOnClickListener {
            clickLambda(currentItem.id)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_activity_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_activity_description)
    }
}