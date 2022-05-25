package com.cyberpanterra.book_2.adapters;

import static com.cyberpanterra.book_2.adapters.ViewHolder.*;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cyberpanterra.book_2.interactions.StaticClass;
import com.cyberpanterra.book_2.R;

import java.util.ArrayList;
import java.util.List;

import com.cyberpanterra.book_2.datas.*;
import com.cyberpanterra.book_2.interfaces.*;
import com.google.android.material.divider.MaterialDividerItemDecoration;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * The creator of the Adapter class is Asadjon Xusanjonov
 * Created on 12:01 PM, 4/27/2022
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {
    public static final String FAVOURITE_FRAGMENT = "FavouriteFragment";
    public static final String MENU_FRAGMENT = "MenuFragment";

    final List<Data> dataList;
    List<Data> fullDataList;
    Action.IRAction<Data, Boolean> onActionListener;
    OnClickListener<Data> onClickListener;
    String searchedText = "";
    final String fragmentName;

    public Adapter(List<Data> dataList, String fragmentName) {
        this.fullDataList = dataList;
        this.dataList = new ArrayList<>(fullDataList);
        this.fragmentName = fragmentName;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(false);
        recyclerView.addItemDecoration(new MaterialDividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        new ItemTouchHelper(getRemoveListener()).attachToRecyclerView(recyclerView);
        super.onAttachedToRecyclerView(recyclerView);
    }

    private ItemTouchHelper.SimpleCallback getRemoveListener() {
        return new ItemTouchHelper.SimpleCallback(0, fragmentName.equals(FAVOURITE_FRAGMENT) ? ItemTouchHelper.LEFT : ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {return false;}

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (onActionListener != null && onActionListener.call(((ViewHolder) viewHolder).binding.getData()))
                    notifyItemChanged(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                final float maxX = .15f;
                final float x = c.getWidth() * maxX * (fragmentName.equals(FAVOURITE_FRAGMENT) ? -1 : 1);
                dX =  Math.abs(dX) < Math.abs(x) ? dX : x;

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(recyclerView.getContext(), android.R.color.holo_red_light))
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(recyclerView.getContext(), R.color.teal_200))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .addSwipeRightActionIcon(((ViewHolder) viewHolder).binding.getData().isFavourite() ? R.drawable.ic_baseline_turned_in_24_light : R.drawable.ic_baseline_turned_in_not_24_light)
                        .setActionIconTint(ContextCompat.getColor(recyclerView.getContext(), R.color.white))
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_data, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(this);
    }

    @Override
    public int getItemCount() {return dataList.size();}

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof Chapter ? CHAPTER_TYPE : (getItem(position) instanceof Theme ? THEME_TYPE : DATA_TYPE);
    }

    public Data getItem(int position) {return dataList.get(position);}

    public int getDataPosition(Data data) {return dataList.indexOf(data);}

    @Override
    public Filter getFilter() {return filter;}

    /**
     * Change the {@code dataList} to the searching text for
     */
    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Data> filteredList = new ArrayList<>();

            // convert charSequence to the upper case
            searchedText = charSequence.toString().toUpperCase().trim();

            // if searchedText is empty, add fullDataList to filteredList
            // otherwise add children who are on the searchedText fullDataList to filteredList
            if (searchedText.isEmpty()) filteredList.addAll(fullDataList);
            else filteredList.addAll(StaticClass.whereAll(fullDataList,
                    data -> (data instanceof Chapter && ((Chapter) data).isSearchResult(searchedText)) ||
                            data.getName().toUpperCase().contains(searchedText) ||
                            data.getValue().toUpperCase().contains(searchedText)));

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List<Data> resultList = (List<Data>) filterResults.values;

            // if the resultList isn't equal to null, dataList is changed
            if (resultList != null) {

                // if result children are empty, removed the dataList children
                if (resultList.isEmpty()) StaticClass.forEach(dataList, Adapter.this::removeData);

                // dataList children update
                StaticClass.forEach(dataList, d -> notifyItemChanged(getDataPosition(d)));

                // dataList children who are not on the resultList will be removed from the dataList children
                if (resultList.size() > dataList.size())
                    StaticClass.forEach(StaticClass.whereAll(resultList, d -> !dataList.contains(d)),
                            Adapter.this::addData);

                    // resultList children who are not on the dataList will be added to the dataList children
                else if (resultList.size() < dataList.size())
                    StaticClass.forEach(StaticClass.whereAll(dataList, d -> !resultList.contains(d)),
                            Adapter.this::removeData);
            }
        }
    };

    /**
     * Add {@code data} to the {@code dataList}
     */
    public void addData(Data data) {
        // if fullDataList does not have data, exit the method
        if (!fullDataList.contains(data) || dataList.contains(data)) return;

        // if data equal to Theme and fullDataList does not have a data's chapter, add data's chapter to dataList
        if (data instanceof Theme && !dataList.contains(((Theme) data).chapter))
            addItem(((Theme) data).chapter);

        // add data to dataList
        addItem(data);
    }

    private void addItem(Data data) {
        if (!fullDataList.contains(data) || dataList.contains(data)) return;

        List<Data> tempList = new ArrayList<>(dataList);
        tempList.add(data);
        List<Data> fullList = new ArrayList<>(fullDataList);
        fullList.retainAll(tempList);
        dataList.add(fullList.indexOf(data), data);

        notifyItemInserted(dataList.indexOf(data));
    }

    public void removeData(Data data) {
        if (!removeItem(data)) return;

        if (data instanceof Chapter)
            StaticClass.forEach(StaticClass.whereAll(dataList, d -> d instanceof Theme && ((Theme) d).chapter.equals(data)), this::removeItem);

        else if (data instanceof Theme && StaticClass.whereAll(dataList, d -> d instanceof Theme && ((Theme) d).chapter.equals(((Theme) data).chapter)).isEmpty())
            removeItem(((Theme) data).chapter);
    }

    private boolean removeItem(Data data) {
        if (!dataList.contains(data)) return false;
        notifyItemRemoved(dataList.indexOf(data));
        return dataList.remove(data);
    }

    public Adapter setOnActionListener(Action.IRAction<Data, Boolean> Listener) {
        onActionListener = Listener;
        return this;
    }

    public Adapter setOnClickListener(OnClickListener<Data> listener) {
        onClickListener = listener;
        return this;
    }
}
