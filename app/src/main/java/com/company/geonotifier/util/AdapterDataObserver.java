package com.company.geonotifier.util;

import androidx.recyclerview.widget.RecyclerView;


public class AdapterDataObserver extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;

    public AdapterDataObserver(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        if (positionStart == 0) recyclerView.scrollToPosition(0);
    }

}
