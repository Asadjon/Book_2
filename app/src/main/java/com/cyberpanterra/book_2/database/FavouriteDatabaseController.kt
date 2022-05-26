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
    private var database = getDatabase()
    var favouriteList = favouriteDatabase

    fun changeTheDatabase() {favouriteDatabase = favouriteList}

    private var favouriteDatabase: List<Data?>
        get() { return (if (database != null) json.serialize(Contents::class.java, database) else Contents()).getExplodedList() }
        set(chapters) { database = json.deserialize(Contents(chapters)) }

    private fun getDatabase(): JSONObject? {
        return try {
            JSONObject(getFileValue())
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    fun saveDatabase(){ saveFile(database.toString()) }

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