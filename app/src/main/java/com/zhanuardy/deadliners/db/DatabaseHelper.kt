package com.zhanuardy.deadliners.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.zhanuardy.deadliners.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.zhanuardy.deadliners.db.DatabaseContract.NoteColumns
import com.zhanuardy.deadliners.entity.Note

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "dbactivity"

        private const val DATABASE_VERSION = 5

        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${NoteColumns.TITLE} TEXT NOT NULL," +
                " ${NoteColumns.DESCRIPTION} TEXT NOT NULL," +
                " ${NoteColumns.VALUE} TEXT NOT NULL," +
                " ${NoteColumns.DATE} TEXT NOT NULL," +
                " ${NoteColumns.TIME} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
        */
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addNewActivity(nameAktivitas: String?, deskripsiAktivitas: String?, valueAktivitas: String?, dateAktivitas: String?, timeAktivitas: String?) {

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
        values.put("TITLE", nameAktivitas)
        values.put("DESCRIPTION", deskripsiAktivitas)
        values.put("VALUE", valueAktivitas)
        values.put("DATE", dateAktivitas)
        values.put("TIME", timeAktivitas)

        // after adding all values we are passing
        // content values to our table.
        db.insert("activity_table", null, values)
//        db.insert("mytable", null, values_DESKRIPSI)
//        db.insert("mytable", null, values_VALUE)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    fun readCourses(): ArrayList<Note> {
        // on below line we are creating a
        // database for reading our database.
        val db: SQLiteDatabase = this.readableDatabase

        // on below line we are creating a cursor with query to
        // read data from database.
        val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // on below line we are creating a new array list.
        val NoteArrayList: ArrayList<Note> = ArrayList()

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                NoteArrayList.add(
                    Note(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)
                    )
                )
            } while (cursorCourses.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close()
        return NoteArrayList
    }

//    fun insertDateTime(dateTime: DateTimeModel) {
//        val values = ContentValues()
//        values.put("DATE", dateTime.date)
//        values.put("TIME", dateTime.time)
//
//        val db = writableDatabase
//        db.insert(TABLE_NAME, null, values)
//        db.close()
//    }
}