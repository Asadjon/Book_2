package com.cyberpanterra.book_2.datas

import com.cyberpanterra.book_2.interactions.StaticClass
import com.cyberpanterra.book_2.json.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

/**
    The creator of the Chapter class is Asadjon Xusanjonov
    Created on 8:38, 23.03.2022
*/
class Chapter() : Data() {
    @JvmField
    @SerializedName("Themes")
    val themeList: MutableList<Theme> = ArrayList()

    fun getThemeList(): List<Theme> {
        return themeList
    }

    fun getFavouritesList(): MutableList<Theme> {
        return StaticClass.whereAll(themeList){it.isFavourite}
    }

    fun isSearchResult(searchingText: String): Boolean {
        return StaticClass.whereAll(themeList) {
            it.name.uppercase(Locale.getDefault()).contains(searchingText) ||
                    it.value.uppercase(Locale.getDefault()).contains(searchingText)
        }.isNotEmpty()
    }
}