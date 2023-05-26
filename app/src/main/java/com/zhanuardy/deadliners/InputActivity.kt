package com.zhanuardy.deadliners

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.EditText
import com.zhanuardy.deadliners.databinding.ActivityInputBinding
import com.zhanuardy.deadliners.databinding.ActivityMainBinding
import java.util.*

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        dateAktivitas()
        jamAktivitas()
        inputData()
    }

    fun inputData(){
        binding.submitAktivitasButton.setOnClickListener{
            val inputnamaText = binding.namaAktivitaseditText.text.toString()
            val inputdeskripsiText = binding.deskripsiAktivitaseditText.text.toString()
            val inputvalueText = binding.nilaiAktivitasspinner.selectedItem.toString()
            dbHelper.addNewActivity(inputnamaText, inputdeskripsiText, inputvalueText)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        val contentValues = ContentValues().apply {
//            put("nameAktivitas", inputnamaText)
//            put("deskripsiAktivitas", inputdeskripsiText)
//            put("valueAktivitas", inputvalueText)
//            // Tambahkan kolom dan nilai data lainnya sesuai skema tabel Anda
//        }
//
//        val tableName = "mytable"
//
//        binding.submitAktivitasButton.setOnClickListener{
//            db.insert(tableName, null, contentValues)
//            db.close()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun dateAktivitas(){
        binding.dateAktivitasEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.dateAktivitasEditText.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }
    }

    fun jamAktivitas(){
        binding.jameditTextTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.jameditTextTime.setText(selectedTime)
            }, hour, minute, true)

            timePickerDialog.show()
        }
    }

}