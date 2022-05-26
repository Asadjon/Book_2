package com.cyberpanterra.book_2.interactions;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.cyberpanterra.book_2.interfaces.Action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *    The creator of the StaticClass class is Asadjon Xusanjonov
 *    Created on 16:02, 24.03.2022
 */
public class StaticClass {

    public static Spannable setHighLightedText(String text, String textToHighlight, int color) {
        String textUpperCase = text.toUpperCase().trim();
        Spannable wordToSpan = new SpannableString(text);

        if (textToHighlight.isEmpty() || !textUpperCase.contains(textToHighlight.toUpperCase().trim())) return wordToSpan;

        for (int i = 0; i < textUpperCase.length() && (i = textUpperCase.indexOf(textToHighlight, i)) >= 0;)
            wordToSpan.setSpan(new BackgroundColorSpan(color), i, i += textToHighlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return wordToSpan;
    }

    @BindingAdapter({"data_text", "text_to_highlight", "color_of_highlight"})
    public static void setHighLightedText(TextView textView, String text, String textToHighlight, int color) {
        textView.setText(setHighLightedText(text, textToHighlight, color));
    }

    public static <T> void forEach(List<T> list,  Action.IAction<T> function){
        if (list == null || list.isEmpty()) return;
        for(int i = 0; i < list.size(); i++)
                function.call(list.get(i));
    }

    public static <T, T2> List<T2> getListAt(List<T> list, Action.IRAction<T, T2> function){
        List<T2> newList = new ArrayList<>();
        if (list == null || list.isEmpty()) return newList;
        forEach(list, t1 -> newList.add(function.call(t1)));
        return newList;
    }

    public static <T> boolean contains(List<T> list, Action.IRAction<T, Boolean> function){
        if (list == null || list.isEmpty()) return false;
        for(int i = 0; i < list.size(); i++)
            if(function.call(list.get(i))) return true;
        return false;
    }

    public static <T> List<T> whereAll(List<T> list, Action.IRAction<T, Boolean> function){
        List<T> newList = new ArrayList<>();
        if (list == null || list.isEmpty()) return newList;
        forEach(list, t1 -> { if(function.call(t1)) newList.add(t1); });
        return newList;
    }

    public static <T> T first(List<T> list, Action.IRAction<T, Boolean> function){
        if (list == null || list.isEmpty()) return null;
        for(int i = 0; i < list.size(); i++)
            if(function.call(list.get(i))) return list.get(i);
        return null;
    }

    public static <T> boolean trueAll(List<T> list, Action.IRAction<T, Boolean> function){
        if (list == null || list.isEmpty()) return false;
        boolean isChecked = false;
        for(int i = 0; i < list.size(); i++)
            isChecked = function.call(list.get(i)) || isChecked;
        return isChecked;
    }

    public static  <T, T2> List<T2> getJsonListAt(JSONArray jsonArray, Action.IRAction<T, T2> action) throws JSONException {
        List<T2> list = new ArrayList<>();
        if (jsonArray == null) return list;

        for (int i = 0; i < jsonArray.length(); i++)
            list.add(action.call((T) jsonArray.get(i)));
        return list;
    }

    public static  <T, T2 extends JSONObject> JSONArray getJsonListAt(List<T> list, Action.IRAction<T, T2> action){
        JSONArray jsonArray = new JSONArray();
        if (list == null) return jsonArray;

        forEach(list, target -> jsonArray.put(action.call(target)));
        return jsonArray;
    }
}
