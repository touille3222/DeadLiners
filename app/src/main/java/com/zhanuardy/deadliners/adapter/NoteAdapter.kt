package com.zhanuardy.deadliners.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhanuardy.deadliners.UpdateDeleteActivity
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
        holder.titleTV.text = modal.title
        holder.descriptionTV.text = modal.description
        holder.dateTV.text = modal.date
        holder.kuadranTV.text = modal.kuadran
        holder.itemView.setOnClickListener { // on below line we are calling an intent.
            val i = Intent(context, UpdateDeleteActivity::class.java)

            // below we are passing all our values.
            i.putExtra("name", modal.title)
            i.putExtra("description", modal.description)
            i.putExtra("value", modal.value)
            i.putExtra("timing", modal.timing)
            i.putExtra("date", modal.date)
            i.putExtra("time", modal.time)
            i.putExtra("kuadran", modal.kuadran)

            // starting our activity.
            context.startActivity(i)
        }
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
        val kuadranTV: TextView

        init {
            // initializing our text views
            titleTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_title)
            descriptionTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_description)
            dateTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_date)
            kuadranTV = itemView.findViewById<TextView>(com.zhanuardy.deadliners.R.id.tv_item_kuadran)
        }
    }
}
