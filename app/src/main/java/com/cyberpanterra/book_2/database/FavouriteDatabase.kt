package com.cyberpanterra.book_2.database

import android.content.Context

/**
 * The creator of the FavouriteDatabase class is Asadjon Xusanjonov
 * Created on 10:39, 26.03.2022
 */
class FavouriteDatabase private constructor(context: Context) {
    val favourites: Favourites

    companion object {
        private var database: FavouriteDatabase? = null
        @JvmStatic
        fun init(context: Context): FavouriteDatabase {
            return database ?: FavouriteDatabase(context).also { database = it }
        }
    }

    init {
        favourites = Favourites(context)
    }
}