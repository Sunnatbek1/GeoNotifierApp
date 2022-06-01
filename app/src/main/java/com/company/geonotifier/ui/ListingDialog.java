package com.company.geonotifier.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.geonotifier.R;
import com.company.geonotifier.util.AdapterDataObserver;

public abstract class ListingDialog extends DialogFragment {

    protected LinearLayout emptyMessageLayout;
    private RecyclerView recyclerView;

    protected NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflateFragment(inflater, container, savedInstanceState);
    }

    protected abstract View inflateFragment(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        emptyMessageLayout = view.findViewById(R.id.empty_msg_layout);
        recyclerView = view.findViewById(R.id.recycler_view);

        configureRecyclerView();
    }

    private void configureRecyclerView() {
        initAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(getAdapter());

        getAdapter().registerAdapterDataObserver(new AdapterDataObserver(recyclerView));
    }

    protected abstract void initAdapter();

    protected abstract RecyclerView.Adapter getAdapter();
}
