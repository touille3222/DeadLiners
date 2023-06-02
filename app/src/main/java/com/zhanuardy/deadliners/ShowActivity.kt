package com.zhanuardy.deadliners

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhanuardy.deadliners.adapter.NoteAdapter
import com.zhanuardy.deadliners.db.DatabaseHelper
import com.zhanuardy.deadliners.entity.Note


class ShowActivity : AppCompatActivity() {
    private var NoteArrayList: ArrayList<Note>? = null
    private var DatabaseHelper: DatabaseHelper? = null
    private var NoteAdapter: NoteAdapter? = null
    private lateinit var coursesRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        // initializing our all variables.
        NoteArrayList = ArrayList()
        DatabaseHelper = DatabaseHelper(this@ShowActivity)

        // getting our course array
        // list from db handler class.
        NoteArrayList = DatabaseHelper!!.readCourses()

        // on below line passing our array list to our adapter class.
        NoteAdapter = NoteAdapter(NoteArrayList!!, this@ShowActivity)
        coursesRV = findViewById<RecyclerView>(R.id.rv_notes)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ShowActivity, RecyclerView.VERTICAL, false)
        coursesRV.layoutManager = linearLayoutManager

        // setting our adapter to recycler view.
        coursesRV.adapter = NoteAdapter
    }
}