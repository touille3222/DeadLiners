package com.zhanuardy.deadliners

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhanuardy.deadliners.databinding.ActivityMainBinding
import com.zhanuardy.deadliners.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var categoriesList: ArrayList<ActivityDataClass>
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        // Fetch categories
        var categoriesCursor: Cursor? = dbHelper.rawQuery("SELECT category_id, category_name FROM categories")
        var categoriesSize: Int = categoriesCursor!!.count
        Log.d("listCategories()", "categoriesSize=" + categoriesSize)

        // Add a list of categories
        categoriesList = ArrayList<ActivityDataClass>()
        while (categoriesCursor.moveToNext()) {
            val categoryId = categoriesCursor.getInt(0)
            val categoryName = categoriesCursor.getString(1)
            Log.d("listCategories()", "categoryId=" + categoryId + " categoryName=" + categoryName)
            categoriesList.add(ActivityDataClass(categoryId, categoryName))

        }

        // Add to list
        recycler_view.adapter = Adapter(categoriesList) {
            categoriesList[it]
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }
}