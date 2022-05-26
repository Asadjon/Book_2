package com.cyberpanterra.book_2.datas;

import java.lang.System;

/**
 * The creator of the Chapter class is Asadjon Xusanjonov
 * Created on 8:38, 23.03.2022
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R0\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/cyberpanterra/book_2/datas/Chapter;", "Lcom/cyberpanterra/book_2/datas/Data;", "()V", "themes", "", "Lcom/cyberpanterra/book_2/datas/Theme;", "themeList", "getThemeList", "()Ljava/util/List;", "setThemeList", "(Ljava/util/List;)V", "isSearchResult", "", "searchingText", "", "app_debug"})
public final class Chapter extends com.cyberpanterra.book_2.datas.Data {
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmField
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "Themes")
    public java.util.List<com.cyberpanterra.book_2.datas.Theme> themes;
    
    public Chapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.cyberpanterra.book_2.datas.Theme> getThemeList() {
        return null;
    }
    
    public final void setThemeList(@org.jetbrains.annotations.NotNull
    java.util.List<com.cyberpanterra.book_2.datas.Theme> themes) {
    }
    
    @java.lang.Override
    public boolean isSearchResult(@org.jetbrains.annotations.NotNull
    java.lang.String searchingText) {
        return false;
    }
}