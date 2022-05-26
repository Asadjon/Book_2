package com.cyberpanterra.book_2.datas;

import java.lang.System;

/**
 * The creator of the Data class is Asadjon Xusanjonov
 * Created on 12:00, 07.04.2022
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000\u00a2\u0006\u0002\u0010\u0003B9\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0013\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0016J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010\u001d\u001a\u00020\u0007H\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014\u00a8\u0006\u001e"}, d2 = {"Lcom/cyberpanterra/book_2/datas/Data;", "", "newData", "(Lcom/cyberpanterra/book_2/datas/Data;)V", "id", "", "name", "", "value", "isFavourite", "", "pages", "Lcom/cyberpanterra/book_2/datas/Pages;", "(ILjava/lang/String;Ljava/lang/String;ZLcom/cyberpanterra/book_2/datas/Pages;)V", "getId", "()I", "()Z", "setFavourite", "(Z)V", "getName", "()Ljava/lang/String;", "getPages", "()Lcom/cyberpanterra/book_2/datas/Pages;", "getValue", "equals", "other", "hashCode", "isSearchResult", "searchingText", "toString", "app_debug"})
public class Data {
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "Id")
    private final int id = 0;
    @org.jetbrains.annotations.NotNull
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "Name")
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "Value")
    private final java.lang.String value = null;
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "isFavourite")
    private boolean isFavourite;
    @org.jetbrains.annotations.NotNull
    @com.cyberpanterra.book_2.json.annotations.SerializedName(value = "Pages")
    private final com.cyberpanterra.book_2.datas.Pages pages = null;
    
    @kotlin.jvm.JvmOverloads
    public Data() {
        super();
    }
    
    @kotlin.jvm.JvmOverloads
    public Data(int id) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads
    public Data(int id, @org.jetbrains.annotations.NotNull
    java.lang.String name) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads
    public Data(int id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads
    public Data(int id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String value, boolean isFavourite) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads
    public Data(int id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String value, boolean isFavourite, @org.jetbrains.annotations.NotNull
    com.cyberpanterra.book_2.datas.Pages pages) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getValue() {
        return null;
    }
    
    public final boolean isFavourite() {
        return false;
    }
    
    public final void setFavourite(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.cyberpanterra.book_2.datas.Pages getPages() {
        return null;
    }
    
    public Data(@org.jetbrains.annotations.NotNull
    com.cyberpanterra.book_2.datas.Data newData) {
        super();
    }
    
    public boolean isSearchResult(@org.jetbrains.annotations.NotNull
    java.lang.String searchingText) {
        return false;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
}