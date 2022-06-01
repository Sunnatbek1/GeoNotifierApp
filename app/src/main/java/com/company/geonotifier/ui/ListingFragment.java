package com.company.geonotifier.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.company.geonotifier.R;
import com.company.geonotifier.util.AdapterDataObserver;
import com.company.geonotifier.util.DevicePrefs;

public abstract class ListingFragment extends BaseFragment {

    private static final String PREF_KEY_LISTING_LAYOUT = "com.company.geonotifier.PREF_KEY_LISTING_LAYOUT";
    private static final int GRID_LAYOUT = 0;
    private static final int LINEAR_LAYOUT = 1;

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected LinearLayout emptyMessageLayout;
    private RecyclerView recyclerView;
    protected ExtendedFloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        emptyMessageLayout = view.findViewById(R.id.empty_msg_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);

        initViewModel();
        initListObserver();
        configureRecyclerView();
        setSwipeRefreshListener();
    }

    protected abstract void initViewModel();

    private void configureRecyclerView() {
        initAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(getAdapter());

        getAdapter().registerAdapterDataObserver(new AdapterDataObserver(recyclerView));
        setRecyclerViewLayoutManager();
        setItemTouchHelper();
        setRecyclerViewScrollListener();
    }

    private void setRecyclerViewLayoutManager() {
        if (getLayoutPref() == GRID_LAYOUT) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else if (getLayoutPref() == LINEAR_LAYOUT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
    }

    protected abstract void initAdapter();

    protected abstract <T extends RecyclerView.Adapter> T getAdapter();

    protected abstract void initListObserver();

    private void setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (fab.isExtended()) {
                        fab.shrink();
                    }
                } else if (dy < 0) {
                    if (!fab.isExtended()) {
                        fab.extend();
                    }
                }
            }
        });
    }

    private void setItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                onAdapterItemSwiped(viewHolder, direction);
            }
        }).attachToRecyclerView(recyclerView);
    }

    protected abstract void onAdapterItemSwiped(RecyclerView.ViewHolder viewHolder, int direction);

    private void setSwipeRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(() -> initListObserver());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.listing_menu, menu);

        swapLayoutMenuIcon(menu.getItem(0));

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                onDeleteAllOptionSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract void onDeleteAllOptionSelected();

    protected abstract void navigateSettings();

    private void swapLayoutPref() {
        if (getLayoutPref() == GRID_LAYOUT) {
            setLayoutPref(LINEAR_LAYOUT);
        } else if (getLayoutPref() == LINEAR_LAYOUT) {
            setLayoutPref(GRID_LAYOUT);
        }
    }

    private void swapLayoutMenuIcon(MenuItem menuItem) {
        if (getLayoutPref() == GRID_LAYOUT) {
            menuItem.setIcon(R.drawable.ic_linear_layout);
        } else if (getLayoutPref() == LINEAR_LAYOUT) {
            menuItem.setIcon(R.drawable.ic_grid_layout);
        }
    }

    private int getLayoutPref() {
        return DevicePrefs.getPrefs(requireContext(), PREF_KEY_LISTING_LAYOUT, LINEAR_LAYOUT);
    }

    private void setLayoutPref(int layout) {
        DevicePrefs.setPrefs(requireContext(), PREF_KEY_LISTING_LAYOUT, layout);
    }
}