package com.cyberpanterra.book_2.adapters;

import static com.cyberpanterra.book_2.adapters.ViewHolder.CHAPTER_TYPE;
import static com.cyberpanterra.book_2.adapters.ViewHolder.DATA_TYPE;
import static com.cyberpanterra.book_2.adapters.ViewHolder.THEME_TYPE;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cyberpanterra.book_2.interactions.StaticClass;
import com.cyberpanterra.book_2.R;

import java.util.ArrayList;
import java.util.List;

import com.cyberpanterra.book_2.datas.Chapter;
import com.cyberpanterra.book_2.datas.Data;
import com.cyberpanterra.book_2.datas.Theme;
import com.cyberpanterra.book_2.interfaces.OnActionListener;
import com.cyberpanterra.book_2.interfaces.OnClickListener;

/**
 * The creator of the Adapter class is Asadjon Xusanjonov
 * Created on 12:01 PM, 4/27/2022
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {

    final List<Data> dataList = new ArrayList<>();
    List<Data> fullDataList = new ArrayList<>();
    OnActionListener<Adapter, Data> onActionListener;
    OnClickListener<Data> onClickListener;
    String searchedText = "";

    public Adapter(List<Data> dataList) {
        this.fullDataList = dataList;
        this.dataList.clear();
        this.dataList.addAll(fullDataList);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(false);
        new ItemTouchHelper(onRemoveListener).attachToRecyclerView(recyclerView);
        super.onAttachedToRecyclerView(recyclerView);
    }

    private final ItemTouchHelper.SimpleCallback onRemoveListener = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {return false;}

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (onActionListener != null) {
                ViewHolder holder = (ViewHolder) viewHolder;
                onActionListener.call(Adapter.this, holder.binding.getData());
            }
        }
    };

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

        List<Data> tempList = new ArrayList<>();
        dataList.add(data);
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

        if (getItemCount() >= 1)
            notifyItemChanged(getItemCount() - 1);
    }

    private boolean removeItem(Data data) {
        if (!dataList.contains(data)) return false;
        notifyItemRemoved(dataList.indexOf(data));
        return dataList.remove(data);
    }

    public Adapter setOnActionListener(OnActionListener<Adapter, Data> Listener) {
        onActionListener = Listener;
        return this;
    }

    public Adapter setOnClickListener(OnClickListener<Data> listener) {
        onClickListener = listener;
        return this;
    }
}
