package com.zhanuardy.deadliners

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "DeadLiners.db"
        private const val DATABASE_VERSION = 8
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Membuat tabel atau inisialisasi skema database
        // Contoh: db.execSQL("CREATE TABLE nama_tabel (kolom1 tipe_data, kolom2 tipe_data);");
        val createTableQuery = "CREATE TABLE IF NOT EXISTS mytable (id INTEGER PRIMARY KEY, nameAktivitas TEXT, deskripsiAktivitas TEXT, valueAktivitas TEXT, dateAktivitas DATE, jamAktivitas TIME)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Melakukan pembaruan skema jika diperlukan
        val dropTableQuery = "DROP TABLE IF EXISTS mytable"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun addNewActivity(nameAktivitas: String?, deskripsiAktivitas: String?, valueAktivitas: String?) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()
//        val values_DESKRIPSI = ContentValues()
//        val values_VALUE = ContentValues()


        // on below line we are passing all values
        // along with its key and value pair.
        values.put("nameAktivitas", nameAktivitas)
        values.put("deskripsiAktivitas", deskripsiAktivitas)
        values.put("valueAktivitas", valueAktivitas)

        // after adding all values we are passing
        // content values to our table.
        db.insert("mytable", null, values)
//        db.insert("mytable", null, values_DESKRIPSI)
//        db.insert("mytable", null, values_VALUE)

        // at last we are closing our
        // database after adding database.
        db.close()
    }
}