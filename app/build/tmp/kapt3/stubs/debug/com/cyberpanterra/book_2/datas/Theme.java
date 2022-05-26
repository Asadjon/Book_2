package com.cyberpanterra.book_2.datas;

import java.lang.System;

/**
 * The creator of the Theme class is Asadjon Xusanjonov
 * Created on 8:43, 23.03.2022
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0004B=\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\bH\u0016R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/cyberpanterra/book_2/datas/Theme;", "Lcom/cyberpanterra/book_2/datas/Data;", "()V", "newTheme", "(Lcom/cyberpanterra/book_2/datas/Data;)V", "chapter", "Lcom/cyberpanterra/book_2/datas/Chapter;", "id", "", "name", "", "value", "isFavourite", "", "pages", "Lcom/cyberpanterra/book_2/datas/Pages;", "(Lcom/cyberpanterra/book_2/datas/Chapter;ILjava/lang/String;Ljava/lang/String;ZLcom/cyberpanterra/book_2/datas/Pages;)V", "equals", "other", "", "hashCode", "app_debug"})
public final class Theme extends com.cyberpanterra.book_2.datas.Data {
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmField
    @com.cyberpanterra.book_2.json.annotations.Deserializable(deserialize = false)
    @com.cyberpanterra.book_2.json.annotations.Serializable(serialize = false)
    public com.cyberpanterra.book_2.datas.Chapter chapter;
    
    public Theme() {
        super(null);
    }
    
    public Theme(@org.jetbrains.annotations.NotNull
    com.cyberpanterra.book_2.datas.Data newTheme) {
        super(null);
    }
    
    public Theme(@org.jetbrains.annotations.NotNull
    com.cyberpanterra.book_2.datas.Chapter chapter, int id, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String value, boolean isFavourite, @org.jetbrains.annotations.Nullable
    com.cyberpanterra.book_2.datas.Pages pages) {
        super(null);
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
}