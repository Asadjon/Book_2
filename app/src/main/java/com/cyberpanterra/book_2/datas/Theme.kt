package com.cyberpanterra.book_2.datas

import com.cyberpanterra.book_2.json.annotations.Deserializable
import com.cyberpanterra.book_2.json.annotations.Serializable

/**
*    The creator of the Theme class is Asadjon Xusanjonov
*    Created on 8:43, 23.03.2022
*/
class Theme : Data {
    @JvmField
    @Serializable(serialize = false)
    @Deserializable(deserialize = false)
    var chapter = Chapter()

    constructor() : super()
    constructor(newTheme: Data) : super(newTheme)
    constructor(
        chapter: Chapter,
        id: Int,
        name: String?,
        value: String?,
        isFavourite: Boolean,
        pages: Pages?
    ) : super(id, name!!, value!!, isFavourite, pages!!) {
        this.chapter = chapter
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other) && chapter == (other as Theme).chapter
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + chapter.hashCode()
        return result
    }
}