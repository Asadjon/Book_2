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
    var themes: List<Theme> = ArrayList()

    var themeList: List<Theme>
    get() { return themes }
    set(themes) {
        this.themes = themes
        StaticClass.forEach(this.themes) { it.chapter = this }
    }

    override fun isSearchResult(searchingText: String): Boolean {
        return super.isSearchResult(searchingText) || StaticClass.contains(themeList) {
            it.name.uppercase(Locale.getDefault()).contains(searchingText) ||
                    it.value.uppercase(Locale.getDefault()).contains(searchingText)
        }
    }
}