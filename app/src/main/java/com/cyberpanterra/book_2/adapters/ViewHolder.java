package com.cyberpanterra.book_2.adapters;


import android.annotation.SuppressLint;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.databinding.ItemDataBinding;

/**
 The creator of the ViewHolder class is Asadjon Xusanjonov
 Created on 17:43, 11.04.2022
 */
@SuppressLint("ClickableViewAccessibility")
public class ViewHolder extends RecyclerView.ViewHolder{
    public static final int DATA_TYPE = 0;
    public static final int THEME_TYPE = 1;
    public static final int CHAPTER_TYPE = 2;

    final ItemDataBinding binding;

    public ViewHolder(@NonNull ItemDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindData(Adapter adapter) {
        int position = getAdapterPosition();
        binding.setData(adapter.getItem(position));
        binding.setSearchText(adapter.searchedText);
        binding.setClick(adapter.onClickListener);

        int itemViewType = getItemViewType();
        binding.valueText.setTypeface(null, itemViewType == CHAPTER_TYPE ? Typeface.BOLD : Typeface.ITALIC);
        binding.background.setImageResource(itemViewType == THEME_TYPE ? R.color.theme_color : R.drawable.chapter_color);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) binding.cardView.getLayoutParams();
        params.setMargins(20, itemViewType != THEME_TYPE ? 50 : 5, 20, position >= adapter.getItemCount()-1 ? 50 : 0);
    }
}

