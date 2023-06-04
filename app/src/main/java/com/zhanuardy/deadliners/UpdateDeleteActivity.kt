package com.zhanuardy.deadliners

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhanuardy.deadliners.databinding.ActivityUpdateDeleteBinding
import com.zhanuardy.deadliners.db.DatabaseHelper


class UpdateDeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDeleteBinding
    private lateinit var DBHandler: DatabaseHelper
    private lateinit var courseName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DBHandler = DatabaseHelper(this)
        courseName = intent.getStringExtra("name").toString();

        binding.deleteAktivitasButton.setOnClickListener {
            DBHandler.deleteCourse(courseName)
            Toast.makeText(this@UpdateDeleteActivity, "Deleted", Toast.LENGTH_SHORT)
                .show()
            val i = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}