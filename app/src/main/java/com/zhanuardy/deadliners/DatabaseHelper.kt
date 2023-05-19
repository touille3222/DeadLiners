package com.zhanuardy.deadliners

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "DeadLiners.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Activity.tb"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRIORITY = "priority"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_CLOCK = "clock"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Membuat tabel atau inisialisasi skema database
        // Contoh: db.execSQL("CREATE TABLE nama_tabel (kolom1 tipe_data, kolom2 tipe_data);");
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${TABLE_NAME}" +
                " (${COLUMN_ID} INTEGER PRIMARY KEY," +
                " ${COLUMN_NAME} TEXT," +
                " ${COLUMN_PRIORITY} TEXT," +
                " ${COLUMN_DATE} DATE," +
                " ${COLUMN_CLOCK} TIMESTAMP)"
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Melakukan pembaruan skema jika diperlukan
    }
}