package com.cyberpanterra.book_2.database;

import static com.cyberpanterra.book_2.interactions.StaticClass.*;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cyberpanterra.book_2.datas.Chapter;
import com.cyberpanterra.book_2.datas.Data;
import com.cyberpanterra.book_2.datas.Theme;
import com.cyberpanterra.book_2.interactions.StaticClass;

import java.util.List;

/**
 * The creator of the Favourites class is Asadjon Xusanjonov
 * Created on 10:00, 26.03.2022
 */
public class Favourites {
    final FavouriteDatabaseController database;
    final MutableLiveData<Data> addedData = new MutableLiveData<>();
    final MutableLiveData<Data> removedData = new MutableLiveData<>();

    final List<Data> favouriteDataList;
    final List<Data> dataList;

    public Favourites(Context context) {
        database = FavouriteDatabaseController.Companion.init(context);
        dataList = database.getFavouriteList();
        favouriteDataList = whereAll(dataList, Data::isFavourite);
    }

    public List<Data> getDataList() {return dataList;}

    public List<Data> getFavouriteDataList() {return favouriteDataList;}

    public LiveData<Data> getAddedData() {return addedData;}

    public LiveData<Data> getRemovedData() {return removedData;}

    private void reimportFavouriteList(Data data, MutableLiveData<Data> liveData) {
        data.setFavourite(!data.isFavourite());
        favouriteDataList.clear();
        favouriteDataList.addAll(whereAll(dataList, Data::isFavourite));
        liveData.setValue(data);
    }

    public boolean add(Data favouriteData) {
        if ((favouriteData instanceof Chapter && addChapter((Chapter) favouriteData)) ||
                ((favouriteData instanceof Theme) && addTheme((Theme) favouriteData)) ||
                addData(favouriteData)) {
            database.changeTheDatabase();
            return true;
        }

        return false;
    }

    public boolean remove(Data favouriteData) {
        if ((favouriteData instanceof Theme && removeTheme((Theme) favouriteData)) ||
                (favouriteData instanceof Chapter && removeChapter((Chapter) favouriteData)) ||
                removeData(favouriteData)) {
            database.changeTheDatabase();
            return true;
        }

        return true;
    }

    private boolean addData(Data data) {
        if (data.isFavourite()) return false;
        reimportFavouriteList(data, addedData);
        return true;
    }

    private boolean addChapter(Chapter chapter) {
        if (addData(chapter)) {
            forEach(chapter.getThemeList(), this::addTheme);
            return true;
        }
        else return trueAll(chapter.getThemeList(), theme -> !theme.isFavourite() && addTheme(theme));
    }

    private boolean addTheme(Theme theme) {
        boolean isChapterChanged = addData(theme.chapter);
        return addData(theme) || isChapterChanged;
    }

    private boolean removeData(Data data) {
        if (!data.isFavourite()) return false;
        reimportFavouriteList(data, removedData);
        return true;
    }

    private boolean removeChapter(Chapter chapter) {
        if (!removeData(chapter)) return false;

        forEach(whereAll(chapter.getThemeList(), Theme::isFavourite), this::removeTheme);

        return true;
    }

    private boolean removeTheme(Theme theme) {
        if (!removeData(theme)) return false;

        if (!contains(theme.chapter.getThemeList(), Theme::isFavourite))
            removeData(theme.chapter);

        return true;
    }
}