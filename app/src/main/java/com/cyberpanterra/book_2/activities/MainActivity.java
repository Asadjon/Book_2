package com.cyberpanterra.book_2.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cyberpanterra.book_2.database.FavouriteDatabase;

import com.cyberpanterra.book_2.database.FavouriteRepository;
import com.cyberpanterra.book_2.interactions.StaticClass;

import com.cyberpanterra.book_2.interfaces.IOnBackPressed;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.databinding.ActivityMainBinding;
import com.cyberpanterra.book_2.ui.FavouriteViewModel;
import com.cyberpanterra.book_2.ui.FavouriteViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    public static final String BOOK_NAME = "Book_2.pdf";
    public static final String DATABASE_NAME = "Mundarija.json";
    private static MainActivity activity;
    public static MainActivity getActivity() { return activity; }

    private NavHostFragment mNavHostFragment;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) provideFavouriteViewModelFactory()).get(FavouriteViewModel.class);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_menu, R.id.navigation_saved)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

    @NotNull
    @Contract(" -> new")
    private FavouriteViewModelFactory provideFavouriteViewModelFactory() {
        return new FavouriteViewModelFactory(FavouriteRepository.getInstance(FavouriteDatabase.getInstance(this).getFavouriteThemes()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem_search = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) menuItem_search.getActionView();
        mSearchView.setQueryHint(getResources().getString(R.string.title_search));

        EditText eSearchView = mSearchView.findViewById(androidx.appcompat.R.id.search_src_text);
        eSearchView.setTextColor(getResources().getColor(R.color.white));
        eSearchView.getViewTreeObserver().addOnGlobalLayoutListener(() -> StaticClass.keyboardShown(eSearchView.getRootView()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about)
            startActivity(new Intent(this, AuthorActivity.class));

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = null;
        if (mNavHostFragment != null)
            fragment = mNavHostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment instanceof IOnBackPressed) {
            if (((IOnBackPressed) fragment).onBackPressed()) super.onBackPressed();
        } else super.onBackPressed();
    }
}