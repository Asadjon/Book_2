package com.cyberpanterra.book_2.json

import com.cyberpanterra.book_2.interactions.StaticClass.*
import com.cyberpanterra.book_2.datas.Chapter
import org.json.JSONObject

/**
*    The creator of the CustomJson class is Asadjon Xusanjonov
*    Created on 4:19 PM, 5/6/2022
*/
class CustomJson : Json() {
    override fun <T> serialize(clazz: Class<T>, jsonObject: JSONObject?): T {
        val clazz2 =
            try {
                if (jsonObject != null && jsonObject.has("Class")) Class.forName(jsonObject.getString("Class")) as Class<T> else clazz
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                clazz
            }
        val value = super.serialize(clazz2, jsonObject)
        if (value is Chapter) forEach((value as Chapter).getThemeList()) { it.chapter = value; }
        return value
    }
}