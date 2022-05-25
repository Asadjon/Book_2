package com.cyberpanterra.book_2.json;

import static com.cyberpanterra.book_2.interactions.StaticClass.*;

import androidx.annotation.NonNull;

import com.cyberpanterra.book_2.json.annotations.*;

import org.jetbrains.annotations.Nullable;
import org.json.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

import com.cyberpanterra.book_2.interfaces.Action;

/**
*    The creator of the Json class is Asadjon Xusanjonov
*    Created on 9:24 AM, 5/6/2022
*/
public class Json {

    enum JsonType {
        JsonObject,
        JsonArray,
        Boolean,
        Byte,
        Character,
        Double,
        Float,
        Int,
        Long,
        Short,
        String,
        Array,
        Collection,
        Custom,
        Class,
        Object
    }

    public <T> T serialize(@NonNull Class<T> clazz, JSONObject jsonObject){
        T value = null;
        try {
            value = clazz.newInstance();
            if (jsonObject != null) value = serialize(value, jsonObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return value;
    }

    protected <T> T serialize(T object, JSONObject jsonObject) {
        forEach(getFields(object.getClass()),
                field -> setFieldValue(field, object, getValue(field, jsonObject)));
        return object;
    }

    /**
     * Set the {@code obj} to the {@code field} value of the {@code value}
     * @param field {@code obj} parameters
     * @param obj changes the parameters values
     * @param value the value of the {@code field} parameter
     */
    protected void setFieldValue(Field field, Object obj, @Nullable Object value){
        // value should not be equal to null,
        // if equal to null the field value will not change!
        if (value != null) {
            field.setAccessible(true);
            try {
                field.set(obj, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get the {@code field} value from jsonObject
     */
    protected Object getValue(Field field, JSONObject jsonObject){
        if (jsonObject == null) return null;

        // Get attributes of field
        String serializedName = getSerializedName(field);

        // Check the field serializable and has jsonObject the serializedName?
        if (!isSerialized(field) || !jsonObject.has(serializedName)) return null;

        Object value = null;
        try {
            value = jsonObject.get(serializedName);
        } catch (JSONException e){
            e.printStackTrace();
        }

        switch (getValueType(value)) {

            case JsonObject:
                value = serialize(field.getType(), (JSONObject) value);
                break;

            case JsonArray: {
                Class<?> genericTypeClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

                List<?> listValue;
                try {
                    listValue = getJsonListAt((JSONArray) value,
                            target -> (Object) serialize(genericTypeClass, (JSONObject) target));
                } catch (JSONException e) {
                    e.printStackTrace();
                    value = null;
                    break;
                }

                if (field.getType().isInstance(listValue))
                    value = listValue;
                else if (field.getType().isArray())
                    value = listValue.toArray();
                else value = null;
            }
            break;
        }

        // return serializedName value in jsonObject
        return value;
    }


    public <T> JSONObject deserialize(T object) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getValueType(object) == JsonType.Custom)
                forEach(getFields(object.getClass()), field -> setJsonValue(object, field, jsonObject));
            else jsonObject.put(object.getClass().getSimpleName(), object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    protected void setJsonValue(Object object, Field field, JSONObject jsonObject){
        // Get attributes of field
        String serializedName = getSerializedName(field);

        // Check the field serializable and has jsonObject the serializedName?
        if (!isDeserialized(field)) return;

        try {
            jsonObject.put(serializedName, getFieldValue(object, field));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Object getFieldValue(Object object, Field field) {
        Object value = null;

        try {
            field.setAccessible(true);
            value = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (value == null) return null;

        switch (getValueType(value)) {
            case Custom:
                value = deserialize(value);
                break;
            case Collection:
                value = getJsonListAt((List<?>) value, this::deserialize);
                break;
            case Array:
                value = getJsonListAt(Arrays.asList((Object[]) value), this::deserialize);
                break;
        }

        return value;
    }


    protected Json.JsonType getValueType(Object obj) {
        if (obj instanceof JSONObject) return JsonType.JsonObject;
        else if (obj instanceof JSONArray) return JsonType.JsonArray;
        else if (obj instanceof Boolean) return JsonType.Boolean;
        else if (obj instanceof Byte) return JsonType.Byte;
        else if (obj instanceof Character) return JsonType.Character;
        else if (obj instanceof Double) return JsonType.Double;
        else if (obj instanceof Float) return JsonType.Float;
        else if (obj instanceof Integer) return JsonType.Int;
        else if (obj instanceof Long) return JsonType.Long;
        else if (obj instanceof Short) return JsonType.Short;
        else if (obj instanceof String) return JsonType.String;
        else if (obj.getClass().isArray()) return JsonType.Array;
        else if (obj instanceof Collection) return JsonType.Collection;
        else if (obj.getClass().isAssignableFrom(Object.class)) return JsonType.Object;
        else if (obj.getClass().isAssignableFrom(Class.class)) return JsonType.Object;
        else return JsonType.Custom;
    }

    private List<Field> getFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    protected String getSerializedName(Field field) {
        return getFieldAttribute(SerializedName.class, field, field.getName(), SerializedName::value);
    }

    protected boolean isSerialized(Field field) {
        return getFieldAttribute(Serializable.class, field, true, Serializable::serialize);
    }

    protected boolean isDeserialized(Field field) {
        return getFieldAttribute(Deserializable.class, field, true, Deserializable::deserialize);
    }

    protected <T extends Annotation, T2> T2 getFieldAttribute(Class<T> clazz, Field field, Action.IRAction<T, T2> function) {
        return getFieldAttribute(clazz, field, null, function);
    }

    protected <T extends Annotation, T2> T2 getFieldAttribute(Class<T> clazz, Field field, T2 defaultValue, Action.IRAction<T, T2> function) {
        try {
            return field.isAnnotationPresent(clazz) ? function.call(field.getAnnotation(clazz)) : defaultValue;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
