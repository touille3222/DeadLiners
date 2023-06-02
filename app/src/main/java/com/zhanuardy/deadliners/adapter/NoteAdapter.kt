package com.zhanuardy.deadliners.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhanuardy.deadliners.entity.Note


class NoteAdapter(NoteArrayList: ArrayList<Note>, context: Context) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    // variable for our array list and context
    private val NoteArrayList: ArrayList<Note>
    private val context: Context

    // constructor
    init {
        this.NoteArrayList = NoteArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // on below line we are inflating our layout
        // file for our recycler view items.
        val view: View =
            LayoutInflater.from(parent.context).inflate(com.zhanuardy.deadliners.R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val modal: Note = NoteArrayList[position]
        holder.titleTV.setText(modal.title)
        holder.descriptionTV.setText(modal.description)
        holder.dateTV.setText(modal.date)
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return NoteArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val titleTV: TextView
        val descriptionTV: TextView
        val dateTV: TextView

        init {
            // initializing our text views
            titleTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_title)
            descriptionTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_description)
            dateTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_date)
        }
    }
}
