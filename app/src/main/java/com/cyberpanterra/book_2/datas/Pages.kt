package com.cyberpanterra.book_2.datas

/**
*    The creator of the Pages class is Asadjon Xusanjonov
*   Created on 18:04, 23.03.2022
*/
class Pages {
    @JvmField
    val fromPage = 0
    @JvmField
    val toPage = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val pages = other as Pages
        return fromPage == pages.fromPage &&
                toPage == pages.toPage
    }

    override fun toString(): String {
        return "Pages{" +
                "fromPage=" + fromPage +
                ", toPage=" + toPage +
                '}'
    }

    override fun hashCode(): Int {
        var result = fromPage
        result = 31 * result + toPage
        return result
    }
}