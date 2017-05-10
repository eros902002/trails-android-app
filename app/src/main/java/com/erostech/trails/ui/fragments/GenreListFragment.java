package com.erostech.trails.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erostech.trails.App;
import com.erostech.trails.R;
import com.erostech.trails.adapters.GenreAdapter;
import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.ui.components.DaggerGenreListComponent;
import com.erostech.trails.ui.contracts.GenreListContract;
import com.erostech.trails.ui.modules.GenreListModule;
import com.erostech.trails.ui.presenters.GenreListPresenter;

import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreListFragment extends Fragment implements GenreListContract.View {
    public static final String TAG = GenreListFragment.class.getSimpleName();

    @BindView(R.id.progress_indicator)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;
    @BindView(R.id.error_btn_retry)
    Button mRetryButton;
    @BindView(R.id.error_txt_cause)
    TextView mErrorText;

    private GenreAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Unbinder mUnbinder;

    @Inject
    GenreListPresenter mPresenter;

    public GenreListFragment() {

    }

    public static GenreListFragment newInstance() {

        Bundle args = new Bundle();

        GenreListFragment fragment = new GenreListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        DaggerGenreListComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .genreListModule(new GenreListModule(this))
                .build()
                .inject(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new GenreAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGenres();
            }
        });

        loadGenres();
    }

    private void loadGenres() {
        mPresenter.loadGenres();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showGenres(List<Genre> genres) {
        if (genres != null && !genres.isEmpty()) {
            hideErrorView();
            mProgressBar.setVisibility(View.GONE);

            mAdapter.addAll(genres);
        }
    }

    @Override
    public void showError(Throwable throwable) {
        showErrorView(throwable);
    }

    @Override
    public void showComplete() {

    }

    private void showErrorView(Throwable throwable) {
        if (mErrorLayout.getVisibility() == View.GONE) {
            mErrorLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);

            mErrorText.setText(fetchErrorMessage(throwable));
        }
    }

    private String fetchErrorMessage(Throwable throwable) {
        String errorMessage = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMessage = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMessage = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMessage;
    }

    private void hideErrorView() {
        if (mErrorLayout.getVisibility() == View.VISIBLE) {
            mErrorLayout.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
