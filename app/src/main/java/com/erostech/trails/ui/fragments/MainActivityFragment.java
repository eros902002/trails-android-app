package com.erostech.trails.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erostech.trails.App;
import com.erostech.trails.adapters.CountriesAdapter;
import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;
import com.erostech.trails.adapters.PostsAdapter;
import com.erostech.trails.R;
import com.erostech.trails.ui.components.DaggerMainScreenComponent;
import com.erostech.trails.ui.contracts.MainScreenContract;
import com.erostech.trails.ui.modules.MainScreenModule;
import com.erostech.trails.ui.presenters.MainScreenPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainScreenContract.View {

    private RecyclerView mRecyclerView;
    private CountriesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Country> mCountries;

    @Inject
    MainScreenPresenter mainPresenter;

    public MainActivityFragment() {
        mCountries = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build()
                .inject(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CountriesAdapter(mCountries);
        mRecyclerView.setAdapter(mAdapter);

        mainPresenter.loadCountries();
    }

    @Override
    public void showCountries(List<Country> countries) {
        mCountries.clear();
        mCountries.addAll(countries);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(getActivity(), "Countries loaded succesfully", Toast.LENGTH_LONG).show();
    }
}
