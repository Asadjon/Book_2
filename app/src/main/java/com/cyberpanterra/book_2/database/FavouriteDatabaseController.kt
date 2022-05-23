package com.cyberpanterra.book_2.database

import android.annotation.SuppressLint
import android.content.Context
import com.cyberpanterra.book_2.activities.MainActivity
import com.cyberpanterra.book_2.datas.Contents
import com.cyberpanterra.book_2.datas.Data
import com.cyberpanterra.book_2.json.CustomJson
import org.json.JSONException
import org.json.JSONObject

/**
*    The creator of the FavouriteDatabaseController class is Asadjon Xusanjonov
*    Created on 14:27, 23.03.2022
*/
class FavouriteDatabaseController(context: Context, fileName: String) :
    DatabaseCopyFromAssets(context, fileName) {

    private val json = CustomJson()
    var favouriteList = favouriteDatabase

    fun changeTheDatabase() {favouriteDatabase = favouriteList}

    private var favouriteDatabase: List<Data?>
        get() { return json.serialize(Contents::class.java, jsonObject).getExplodedList() }
        set(chapters) { saveFile(json.deserialize(Contents(chapters)).toString()) }

    private val jsonObject: JSONObject? get() {
        return try {
            JSONObject(getFileValue())
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var database: FavouriteDatabaseController? = null
            private set

        @JvmStatic
        fun init(context: Context): FavouriteDatabaseController {
            if (database == null)
                database = FavouriteDatabaseController(context, MainActivity.DATABASE_NAME)
            return database as FavouriteDatabaseController
        }
    }
}