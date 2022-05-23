package com.cyberpanterra.book_2.datas

import android.os.Build
import android.text.Spannable
import com.cyberpanterra.book_2.interactions.StaticClass
import com.cyberpanterra.book_2.json.annotations.SerializedName
import java.util.*

/**
The creator of the Data class is Asadjon Xusanjonov
Created on 12:00, 07.04.2022
 */
open class Data @JvmOverloads constructor(
    @field:SerializedName("Id") val id: Int = 0,
    @field:SerializedName("Name") val name: String = "",
    @field:SerializedName("Value") val value: String = "",
    @field:SerializedName("isFavourite") var isFavourite: Boolean = false,
    @field:SerializedName("Pages") val pages: Pages = Pages()
) {

    @SerializedName("Class")
    val clazz: Class<*>

    constructor(newData: Data) : this(
        newData.id,
        newData.name,
        newData.value,
        newData.isFavourite,
        newData.pages
    )

    fun getSpannableName(searchText: String?): Spannable {
        return StaticClass.setHighLightedText(name, searchText)
    }

    fun getSpannableValue(searchText: String?): Spannable {
        return StaticClass.setHighLightedText(value, searchText)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val data = other as Data
        return id == data.id && name == data.name && value == data.value && isFavourite == data.isFavourite
    }

    override fun hashCode(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) Objects.hash(id, name, value, isFavourite, pages )
        else super.hashCode()
    }

    override fun toString(): String {
        return this.javaClass.simpleName + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", isFavourite='" + isFavourite + '\'' +
                ", pages=" + pages +
                '}'
    }

    init { clazz = javaClass }
}