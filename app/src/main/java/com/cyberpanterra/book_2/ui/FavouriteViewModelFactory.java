package com.cyberpanterra.book_2.ui;

/* 
    The creator of the FavouriteViewModelFactory class is Asadjon Xusanjonov
    Created on 10:53, 26.03.2022
*/

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cyberpanterra.book_2.database.FavouriteRepository;

import org.jetbrains.annotations.NotNull;

public class FavouriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final FavouriteRepository repository;

    public FavouriteViewModelFactory(FavouriteRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new FavouriteViewModel();
    }
}
