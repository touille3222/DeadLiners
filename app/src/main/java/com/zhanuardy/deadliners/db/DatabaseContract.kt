package com.zhanuardy.deadliners.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class NoteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "activity_table"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val VALUE = "value"
            const val TIMING = "timing"
            const val DATE = "date"
            const val TIME = "time"
        }

    }
}