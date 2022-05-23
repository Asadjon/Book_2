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
import com.cyberpanterra.book_2.interactions.StaticClass;
import com.cyberpanterra.book_2.interfaces.*;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.activities.ViewActivity;
import com.cyberpanterra.book_2.databinding.FragmentMainBinding;

public class FavouriteFragment extends Fragment implements IOnBackPressed {

    private Favourites favourites;
    private FragmentMainBinding binding;
    private Action.IRAction<Void, Boolean> onSearchViewCollapse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentMainBinding.inflate(inflater, container, false)).getRoot();
    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        favourites = FavouriteDatabase.getInstance(requireContext()).getFavouriteThemes();

        binding.setAdapter(new Adapter(favourites.getFavouriteDataList())
                .setOnClickListener(this::OnClick)
                .setOnActionListener(this::OnRemove));

        favouriteEmpty(favourites.getFavouriteDataList().isEmpty());

        favourites.getRemovedData().observe(requireActivity(), data -> {
            binding.getAdapter().removeData(data);
            favouriteEmpty(favourites.getFavouriteDataList().isEmpty());
        });

        favourites.getAddedData().observe(requireActivity(), data -> {
            binding.getAdapter().addData(data);
            favouriteEmpty(favourites.getFavouriteDataList().isEmpty());
        });
    }

    public void OnClick(Data data) {
        Intent intent = new Intent(requireContext(), ViewActivity.class);
        intent.putExtra(ViewActivity.DATA_INDEX, favourites.getDataList().indexOf(data));
        startActivity(intent);
    }

    public void OnRemove(Adapter adapter, Data data) {favourites.remove(data);}

    private boolean searchViewCollapse() {
        try {
            return onSearchViewCollapse != null ? onSearchViewCollapse.call(null) : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem_search = menu.findItem(R.id.action_search);

        onSearchViewCollapse = target -> {
            if (menuItem_search.isActionViewExpanded()) {
                MenuItemCompat.collapseActionView(menuItem_search);
                return false;
            } else return true;
        };

        SearchView mSearchView = (SearchView) menuItem_search.getActionView();
        mSearchView.setOnQueryTextListener(onQueryTextListener);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {return false;}

        @Override
        public boolean onQueryTextChange(String newText) {
            binding.getAdapter().getFilter().filter(newText);
            return false;
        }
    };

    private void favouriteEmpty(boolean isEmpty) {
        binding.setEmptyTextShow(isEmpty);
        if (isEmpty) searchViewCollapse();
    }

    @Override
    public boolean onBackPressed() {return searchViewCollapse();}
}