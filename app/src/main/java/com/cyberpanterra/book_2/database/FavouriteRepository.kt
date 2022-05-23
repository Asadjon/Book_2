package com.cyberpanterra.book_2.database

import androidx.lifecycle.LiveData
import com.cyberpanterra.book_2.datas.Data

/**
 * The creator of the FavouriteRepository class is Asadjon Xusanjonov
 * Created on 10:43, 26.03.2022
 */
class FavouriteRepository private constructor(private val favourites: Favourites) {
    fun addData(data: Data?) {
        favourites.add(data)
    }

    fun removeData(data: Data?) {
        favourites.remove(data)
    }

    companion object {
        private var repository: FavouriteRepository? = null
        @JvmStatic
        fun getInstance(favouriteThemes: Favourites): FavouriteRepository? {
            return if (repository != null) repository else FavouriteRepository(favouriteThemes).also {
                repository = it
            }
        }
    }
}