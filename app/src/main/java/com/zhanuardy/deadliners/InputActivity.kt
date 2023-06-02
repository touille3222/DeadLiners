package com.zhanuardy.deadliners

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.widget.EditText
import com.zhanuardy.deadliners.databinding.ActivityInputBinding
import com.zhanuardy.deadliners.databinding.ActivityMainBinding
import com.zhanuardy.deadliners.db.DatabaseContract
import com.zhanuardy.deadliners.db.DatabaseHelper
import com.zhanuardy.deadliners.db.DateTimeModel
import com.zhanuardy.deadliners.entity.Note
import java.util.*

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase
    private var note: Note? = null
    private var position: Int = 0
    var selectedDate: String? = null
    var selectedTime: String? = null

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val RESULT_ADD = 101
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

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
            val inputDate=selectedDate
            val inputTime=selectedTime
            dbHelper.addNewActivity(inputnamaText, inputdeskripsiText, inputvalueText, inputDate, inputTime)
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
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
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
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.jameditTextTime.setText(selectedTime)
            }, hour, minute, true)

            timePickerDialog.show()
        }
    }

}