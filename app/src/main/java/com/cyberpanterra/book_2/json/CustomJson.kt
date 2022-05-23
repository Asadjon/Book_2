package com.cyberpanterra.book_2.json

import com.cyberpanterra.book_2.datas.Chapter
import com.cyberpanterra.book_2.datas.Data
import com.cyberpanterra.book_2.interactions.StaticClass
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Field

/**
    The creator of the CustomJson class is Asadjon Xusanjonov
    Created on 4:19 PM, 5/6/2022
*/
class CustomJson : Json() {
    override fun <T> serialize(clazz: Class<T>, jsonObject: JSONObject?): T {
        val clazz2 =
            try {
                if (jsonObject!!.has("Class")) Class.forName(jsonObject.getString("Class")) as Class<T> else clazz
            } catch (e: Exception) {
                e.printStackTrace()
                clazz
            }
        val value = super.serialize(clazz2, jsonObject)
        if (value is Chapter) StaticClass.forEach((value as Chapter).getThemeList()) {
            setFieldValue(it.javaClass.getField("chapter"), it, value)
        }
        return value
    }

    override fun setFieldValue(field: Field, obj: Any, value: Any) {
        if (field.type != Class::class.java)
            super.setFieldValue(field, obj, value)
    }

    override fun <T> deserialize(obj: T): JSONObject {
        val value = super.deserialize(obj)
        if (obj is Data && value.has("Class"))
            try {
                value.put("Class", obj.javaClass.name)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        return value
    }
}