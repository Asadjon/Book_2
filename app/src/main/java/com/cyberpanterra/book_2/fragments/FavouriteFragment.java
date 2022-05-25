package com.cyberpanterra.book_2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.cyberpanterra.book_2.activities.MainActivity;
import com.cyberpanterra.book_2.adapters.Adapter;
import com.cyberpanterra.book_2.database.FavouriteDatabase;
import com.cyberpanterra.book_2.database.Favourites;
import com.cyberpanterra.book_2.datas.*;
import com.cyberpanterra.book_2.interfaces.*;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.activities.ViewActivity;
import com.cyberpanterra.book_2.databinding.FragmentMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class FavouriteFragment extends Fragment implements IOnBackPressed {

    private Favourites favourites;
    private FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentMainBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        favourites = FavouriteDatabase.getInstance(requireContext()).getFavouriteThemes();

        binding.setAdapter(new Adapter(favourites.getFavouriteDataList(), Adapter.FAVOURITE_FRAGMENT)
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

    private void OnClick(Data data) {
        startActivity(new Intent(requireContext(), ViewActivity.class)
                .putExtra(ViewActivity.DATA_INDEX, favourites.getDataList().indexOf(data)));
    }

    private boolean OnRemove(Data data) {
        if (!favourites.remove(data)) return false;
        Snackbar.make(binding.recyclerView, "Removed - " + data.getName() + ": " + data.getValue(), Snackbar.LENGTH_SHORT)
                .setAction(binding.recyclerView.getContext().getResources().getString(R.string.title_undo), view -> favourites.add(data)).show();
        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem_search = menu.findItem(R.id.action_search);

        Observer<Data> observer = data -> menuItem_search.setEnabled(!favourites.getFavouriteDataList().isEmpty());
        observer.onChanged(null);

        favourites.getAddedData().observe(this, observer);
        favourites.getRemovedData().observe(this, observer);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity.getActivity().searchViewCollapse();
        MainActivity.getActivity().getOnQueryTextChange().put(Adapter.FAVOURITE_FRAGMENT, newText ->
                binding.getAdapter().getFilter().filter(newText));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.getActivity().getOnQueryTextChange().remove(Adapter.FAVOURITE_FRAGMENT);
    }

    private void favouriteEmpty(boolean isEmpty) {
        binding.setEmptyTextShow(isEmpty);
        if (isEmpty) MainActivity.getActivity().searchViewCollapse();
    }

    @Override
    public boolean onBackPressed() {return true;}
}