package com.cyberpanterra.book_2.activities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cyberpanterra.book_2.database.FavouriteDatabase;
import com.cyberpanterra.book_2.database.Favourites;
import com.cyberpanterra.book_2.datas.*;

import com.cyberpanterra.book_2.R;
import com.cyberpanterra.book_2.databinding.ActivityViewBinding;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FileUtils;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class ViewActivity extends AppCompatActivity {
    public static final String DATA_INDEX = "com.cyberpanterra.book_2.DATA_INDEX";

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;

    private ActivityViewBinding binding;
    private Favourites favourites;
    private Data data;
    private ActionBar actionBar;

    private final Handler mHideHandler = new Handler();
    @SuppressLint("InlinedApi")
    private final Runnable mHidePart2Runnable = () -> binding.pdfViewer.setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LOW_PROFILE
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    private final Runnable mShowPart2Runnable = () -> {
        // Delayed display of com.cyberpanterra.book_2.UI elements
        if (actionBar != null) actionBar.show();
    };
    private boolean mVisible = true;
    private final Runnable mHideRunnable = this::hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        favourites = FavouriteDatabase.init(this).getFavourites();
        data = favourites.getDataList().get(getIntent().getIntExtra(DATA_INDEX, 0));

        actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(data.getName() + (data.getValue().isEmpty() ? "" : " - " + data.getValue()));

        int[] pages = new int[data.getPages().toPage - data.getPages().fromPage + 1];
        for (int i = 0; i < pages.length; i++) pages[i] = data.getPages().fromPage + i - 1;
        loadBook(pages);
    }

    private void loadBook(int... pages){
        PDFView.Configurator configurator;
        try {
            configurator = binding.pdfViewer.fromFile(FileUtils.fileFromAsset(this, MainActivity.BOOK_NAME));
            binding.pdfViewer.setOnClickListener(view -> toggle());
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            return;
        }

        configurator.pages(pages)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        MenuItem itemFavourite = menu.findItem(R.id.action_favourite);
        itemFavourite.setIcon(data.isFavourite() ? R.drawable.ic_baseline_turned_in_24_light : R.drawable.ic_baseline_turned_in_not_24_light);

        Observer<? super Data> observer = d ->
                itemFavourite.setIcon(data.isFavourite() ? R.drawable.ic_baseline_turned_in_24_light : R.drawable.ic_baseline_turned_in_not_24_light);

        favourites.getAddedData().observe(this, observer);
        favourites.getRemovedData().observe(this, observer);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        else if (item.getItemId() == R.id.action_favourite) changeFavourite();

        return super.onOptionsItemSelected(item);
    }

    private void changeFavourite() {
        if (data.isFavourite()) favourites.remove(data);
        else favourites.add(data);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        show();
    }

    private void toggle() {
        if (mVisible) hide();
        else show();
    }

    private void hide() {
        // Hide com.cyberpanterra.book_2.UI first
        if (actionBar != null) actionBar.hide();

        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        binding.pdfViewer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display com.cyberpanterra.book_2.UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);

        if (AUTO_HIDE) delayedHide();
    }

    private void delayedHide() {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, ViewActivity.AUTO_HIDE_DELAY_MILLIS);
    }
}