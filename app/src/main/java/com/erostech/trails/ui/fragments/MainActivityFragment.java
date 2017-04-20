package com.erostech.trails.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import android.widget.Toast;

import com.erostech.trails.App;
import com.erostech.trails.adapters.CountriesAdapter;
import com.erostech.trails.adapters.PaginationAdapter;
import com.erostech.trails.callbacks.PaginationAdapterCallback;
import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;
import com.erostech.trails.adapters.PostsAdapter;
import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Result;
import com.erostech.trails.listeners.PaginationScrollListener;
import com.erostech.trails.ui.components.DaggerMainScreenComponent;
import com.erostech.trails.ui.contracts.MainScreenContract;
import com.erostech.trails.ui.modules.MainScreenModule;
import com.erostech.trails.ui.presenters.MainScreenPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainScreenContract.View, PaginationAdapterCallback {

    private static final int PAGE_START = 1;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private PaginationAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LinearLayout mErrorLayout;
    private Button mRetryButton;
    private TextView mErrorText;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    @Inject
    MainScreenPresenter mainPresenter;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_indicator);
        mErrorLayout = (LinearLayout) view.findViewById(R.id.error_layout);
        mRetryButton = (Button) view.findViewById(R.id.error_btn_retry);
        mErrorText = (TextView) view.findViewById(R.id.error_txt_cause);

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
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new PaginationAdapter(getContext(), this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFirstPage();
            }
        });
    }

    @Override
    public void showInitialPage(List<Result> movies) {
        hideErrorView();

        mProgressBar.setVisibility(View.GONE);

        mAdapter.addAll(movies);

        if (currentPage <= TOTAL_PAGES) {
            mAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void showNextPage(List<Result> movies) {
        mAdapter.removeLoadingFooter();
        isLoading = false;

        mAdapter.addAll(movies);

        if (currentPage != TOTAL_PAGES) {
            mAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void showInitialPageError(Throwable throwable) {
        showErrorView(throwable);
    }

    @Override
    public void showNextPageError(Throwable throwable) {
        mAdapter.showRetry(true, fetchErrorMessage(throwable));
    }

    @Override
    public void showComplete() {

    }

    public void loadFirstPage() {
        mainPresenter.loadInitialPage(currentPage);
    }

    public void loadNextPage() {
        mainPresenter.loadNextPage(currentPage);
    }

    @Override
    public void retryPageLoad() {
        loadNextPage();
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
