package com.cyberpanterra.book_2.database;

import java.lang.System;

/**
 * The creator of the FavouriteDatabaseController class is Asadjon Xusanjonov
 * Created on 14:27, 23.03.2022
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\n\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0002J\u0006\u0010\u001a\u001a\u00020\u0018R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\b\tR4\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b8B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/cyberpanterra/book_2/database/FavouriteDatabaseController;", "Lcom/cyberpanterra/book_2/database/DatabaseCopyFromAssets;", "context", "Landroid/content/Context;", "fileName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "database", "Lorg/json/JSONObject;", "database$1", "chapters", "", "Lcom/cyberpanterra/book_2/datas/Data;", "favouriteDatabase", "getFavouriteDatabase", "()Ljava/util/List;", "setFavouriteDatabase", "(Ljava/util/List;)V", "favouriteList", "getFavouriteList", "setFavouriteList", "json", "Lcom/cyberpanterra/book_2/json/CustomJson;", "changeTheDatabase", "", "getDatabase", "saveDatabase", "Companion", "app_debug"})
public final class FavouriteDatabaseController extends com.cyberpanterra.book_2.database.DatabaseCopyFromAssets {
    private final com.cyberpanterra.book_2.json.CustomJson json = null;
    private org.json.JSONObject database$1;
    @org.jetbrains.annotations.NotNull
    private java.util.List<? extends com.cyberpanterra.book_2.datas.Data> favouriteList;
    @org.jetbrains.annotations.NotNull
    public static final com.cyberpanterra.book_2.database.FavouriteDatabaseController.Companion Companion = null;
    @org.jetbrains.annotations.Nullable
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    private static com.cyberpanterra.book_2.database.FavouriteDatabaseController database;
    
    public FavouriteDatabaseController(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String fileName) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.cyberpanterra.book_2.datas.Data> getFavouriteList() {
        return null;
    }
    
    public final void setFavouriteList(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.cyberpanterra.book_2.datas.Data> p0) {
    }
    
    public final void changeTheDatabase() {
    }
    
    private final java.util.List<com.cyberpanterra.book_2.datas.Data> getFavouriteDatabase() {
        return null;
    }
    
    private final void setFavouriteDatabase(java.util.List<? extends com.cyberpanterra.book_2.datas.Data> chapters) {
    }
    
    private final org.json.JSONObject getDatabase() {
        return null;
    }
    
    public final void saveDatabase() {
    }
    
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmStatic
    public static final com.cyberpanterra.book_2.database.FavouriteDatabaseController init(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007R*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@BX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2 = {"Lcom/cyberpanterra/book_2/database/FavouriteDatabaseController$Companion;", "", "()V", "<set-?>", "Lcom/cyberpanterra/book_2/database/FavouriteDatabaseController;", "database", "getDatabase", "()Lcom/cyberpanterra/book_2/database/FavouriteDatabaseController;", "setDatabase", "(Lcom/cyberpanterra/book_2/database/FavouriteDatabaseController;)V", "init", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.cyberpanterra.book_2.database.FavouriteDatabaseController getDatabase() {
            return null;
        }
        
        private final void setDatabase(com.cyberpanterra.book_2.database.FavouriteDatabaseController p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        @kotlin.jvm.JvmStatic
        public final com.cyberpanterra.book_2.database.FavouriteDatabaseController init(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}