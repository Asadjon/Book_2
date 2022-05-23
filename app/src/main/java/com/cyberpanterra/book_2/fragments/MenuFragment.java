package com.cyberpanterra.book_2.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import com.cyberpanterra.book_2.adapters.Adapter;
import com.cyberpanterra.book_2.database.FavouriteDatabase;
import com.cyberpanterra.book_2.database.Favourites;
import com.cyberpanterra.book_2.datas.*;
import com.cyberpanterra.book_2.interfaces.*;
import com.cyberpanterra.book_2.activities.*;
import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.databinding.FragmentMainBinding;

import org.jetbrains.annotations.NotNull;

public class MenuFragment extends Fragment implements IOnBackPressed {

    private Favourites favourites;
    private FragmentMainBinding binding;
    private Action.IRAction<Void, Boolean> onSearchViewCollapse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        favourites = FavouriteDatabase.getInstance(requireContext()).getFavouriteThemes();

        binding.setAdapter(new Adapter(favourites.getDataList(), Adapter.MENU_FRAGMENT)
                .setOnClickListener(this::OnClick)
                .setOnActionListener(this::OnFavourite));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem_search = menu.findItem(R.id.action_search);

        onSearchViewCollapse = target -> {
            if (menuItem_search.isActionViewExpanded()) {
                MenuItemCompat.collapseActionView(menuItem_search);
                return false;
            } else {
                return true;
            }
        };
        searchViewCollapse();

        SearchView mSearchView = (SearchView) menuItem_search.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                binding.getAdapter().getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void OnClick(Data data) {
        Intent intent = new Intent(requireContext(), ViewActivity.class);
        intent.putExtra(ViewActivity.DATA_INDEX, favourites.getDataList().indexOf(data));
        startActivity(intent);
    }

    private boolean OnFavourite(Data data){
        favourites.add(data);
        return true;
    }

    private boolean searchViewCollapse() {
        try {
            return onSearchViewCollapse != null ? onSearchViewCollapse.call(null) : true;
        } catch (Exception e) { e.printStackTrace(); }
        return true;
    }

    @Override
    public boolean onBackPressed() { return searchViewCollapse(); }
}