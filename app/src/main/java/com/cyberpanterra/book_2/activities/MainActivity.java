package com.cyberpanterra.book_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cyberpanterra.book_2.database.FavouriteDatabase;

import com.cyberpanterra.book_2.database.FavouriteRepository;
import com.cyberpanterra.book_2.interactions.StaticClass;

import com.cyberpanterra.book_2.interfaces.Action;
import com.cyberpanterra.book_2.interfaces.IOnBackPressed;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.databinding.ActivityMainBinding;
import com.cyberpanterra.book_2.ui.FavouriteViewModel;
import com.cyberpanterra.book_2.ui.FavouriteViewModelFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String BOOK_NAME = "Book_2.pdf";
    public static final String DATABASE_NAME = "Mundarija.json";
    private static MainActivity activity;
    public static MainActivity getActivity() { return activity; }

    private NavHostFragment navHostFragment;
    private final HashMap<String, Action.IAction<String>> onQueryTextChange = new HashMap<>();
    private Action.IRAction<Void, Boolean> onSearchViewCollapse;

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

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

    @NotNull
    private FavouriteViewModelFactory provideFavouriteViewModelFactory() {
        return new FavouriteViewModelFactory(FavouriteRepository.getInstance(FavouriteDatabase.getInstance(this).getFavouriteThemes()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem_search = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem_search.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.title_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return false;}

            @Override
            public boolean onQueryTextChange(String newText) {
                for (int i = 0; i < onQueryTextChange.values().size(); i++) {
                    Action.IAction<String> onQueryText = (Action.IAction<String>) onQueryTextChange.values().toArray()[i];
                    onQueryText.call(newText);
                }
                return false;
            }
        });

        onSearchViewCollapse = target -> {
            if (menuItem_search.isActionViewExpanded()) {
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setFocusable(false);
                menuItem_search.collapseActionView();
                return false;
            } else return true;
        };

        EditText editTextOfSearchView = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editTextOfSearchView.setTextColor(getResources().getColor(R.color.white));
        editTextOfSearchView.getViewTreeObserver().addOnGlobalLayoutListener(() -> StaticClass.keyboardShown(editTextOfSearchView.getRootView()));
        return super.onCreateOptionsMenu(menu);
    }

    public void searchViewCollapse() {
        if (onSearchViewCollapse != null) {
            onSearchViewCollapse.call(null);
        }
    }

    public HashMap<String, Action.IAction<String>> getOnQueryTextChange() {return onQueryTextChange;}

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
        if (navHostFragment != null)
            fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment instanceof IOnBackPressed) {
            if (((IOnBackPressed) fragment).onBackPressed()) super.onBackPressed();
        } else super.onBackPressed();
    }
}