package com.cyberpanterra.book_2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cyberpanterra.book_2.adapters.Adapter;
import com.cyberpanterra.book_2.database.FavouriteDatabase;
import com.cyberpanterra.book_2.database.Favourites;
import com.cyberpanterra.book_2.interfaces.*;
import com.cyberpanterra.book_2.activities.*;
import com.cyberpanterra.book_2.databinding.FragmentMainBinding;

public class MenuFragment extends Fragment implements IOnBackPressed {

    private Action.IAction<String> onQueryTextChange;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Favourites favourites = FavouriteDatabase.getInstance(requireContext()).getFavouriteThemes();

        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setAdapter(new Adapter(favourites.getDataList(), Adapter.MENU_FRAGMENT)

                .setOnClickListener(data -> startActivity(new Intent(requireContext(), ViewActivity.class)
                        .putExtra(ViewActivity.DATA_INDEX, favourites.getDataList().indexOf(data))))

                .setOnActionListener(data -> {
                    favourites.add(data);
                    return true;
                }));

        onQueryTextChange = newText -> binding.getAdapter().getFilter().filter(newText);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity.getActivity().searchViewCollapse();
        MainActivity.getActivity().getOnQueryTextChange().put(Adapter.MENU_FRAGMENT, onQueryTextChange);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.getActivity().getOnQueryTextChange().remove(Adapter.MENU_FRAGMENT);
    }

    @Override
    public boolean onBackPressed() { return true; }
}