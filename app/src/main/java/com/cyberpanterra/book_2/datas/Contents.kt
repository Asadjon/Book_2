package com.cyberpanterra.book_2.datas

import com.cyberpanterra.book_2.interactions.StaticClass
import com.cyberpanterra.book_2.json.annotations.SerializedName

/**
*    The creator of the Contents class is Asadjon Xusanjonov
*    Created on 5:04 PM, 5/6/2022
*/
class Contents @JvmOverloads constructor(chapterList: List<Data?>? = ArrayList()) {

    @SerializedName("Contents")
    val chapterList: List<Data?>

    fun getExplodedList(): List<Data?> {
        return exploded(chapterList)
    }

    private fun exploded(dataList: List<Data?>?): List<Data?> {
        val explodeDataList: MutableList<Data?> = ArrayList()
        StaticClass.forEach(dataList) { data: Data? ->
            explodeDataList.add(data)
            if (data is Chapter) explodeDataList.addAll(data.themeList)
        }
        return explodeDataList
    }

    private fun assembled(dataList: List<Data?>?): List<Data?> {
        val assembleDataList: MutableList<Data?> = ArrayList()
        StaticClass.forEach(dataList) { data: Data? -> if (data !is Theme) assembleDataList.add(data) }
        return assembleDataList
    }

    init {
        this.chapterList = assembled(chapterList)
    }
}