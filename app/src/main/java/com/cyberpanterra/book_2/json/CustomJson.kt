package com.cyberpanterra.book_2.json

import com.cyberpanterra.book_2.datas.Chapter
import com.cyberpanterra.book_2.datas.Data
import com.cyberpanterra.book_2.datas.Theme
import org.json.JSONObject
import java.lang.reflect.Field

/**
 *    The creator of the CustomJson class is Asadjon Xusanjonov
 *    Created on 4:19 PM, 5/6/2022
 */
class CustomJson : Json() {
    override fun <T> serialize(clazz: Class<T>, jsonObject: JSONObject?): T {
        return super.serialize(
            try {
                if (jsonObject != null && jsonObject.has("Class")) Class.forName(jsonObject.getString("Class")) as Class<T> else clazz
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                clazz
            }, jsonObject)
    }

    override fun setFieldValue(field: Field, obj: Any, value: Any?) {
        if (obj is Chapter && field.type.equals(List::class.java) && value != null)
            obj.themeList = value as List<Theme>
        else super.setFieldValue(field, obj, value)
    }

    override fun <T : Any?> deserialize(obj: T): JSONObject {
        val jsonObject = super.deserialize(obj)
        if (obj is Data) jsonObject.put("Class", obj.javaClass.name)
        return jsonObject
    }
}