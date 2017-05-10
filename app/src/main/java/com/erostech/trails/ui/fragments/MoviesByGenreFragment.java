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

import com.erostech.trails.App;
import com.erostech.trails.Constants;
import com.erostech.trails.R;
import com.erostech.trails.adapters.PaginationAdapter;
import com.erostech.trails.callbacks.PaginationAdapterCallback;
import com.erostech.trails.config.Config;
import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.core.data.models.Movie;
import com.erostech.trails.listeners.PaginationScrollListener;
import com.erostech.trails.ui.components.DaggerMoviesByGenreComponent;
import com.erostech.trails.ui.contracts.MoviesByGenreContract;
import com.erostech.trails.ui.modules.MoviesByGenreModule;
import com.erostech.trails.ui.presenters.MoviesByGenrePresenter;

import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesByGenreFragment extends Fragment implements MoviesByGenreContract.View, PaginationAdapterCallback {
    public static final String TAG = MoviesByGenreFragment.class.getSimpleName();

    private static final int PAGE_START = 1;

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

    private PaginationAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Genre mGenre;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int totalPages = 5;
    private int currentPage = PAGE_START;

    private Unbinder mUnbinder;

    @Inject
    MoviesByGenrePresenter mPresenter;

    public static MoviesByGenreFragment newInstance(Genre genre) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARGUMENT_GENRE, genre);
        MoviesByGenreFragment fragment = new MoviesByGenreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesByGenreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_by_genre, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        DaggerMoviesByGenreComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .moviesByGenreModule(new MoviesByGenreModule(this))
                .build()
                .inject(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!getArguments().containsKey(Constants.ARGUMENT_GENRE)) {
            getActivity().finish();
        } else {
            mGenre = getArguments().getParcelable(Constants.ARGUMENT_GENRE);

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
                    return totalPages;
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

            mRetryButton.setOnClickListener(v -> loadFirstPage());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showInitialPage(int totalPages, List<Movie> movies) {
        hideErrorView();

        mProgressBar.setVisibility(View.GONE);

        this.totalPages = totalPages;

        mAdapter.addAll(movies);

        if (currentPage <= totalPages) {
            mAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void showNextPage(List<Movie> movies) {
        mAdapter.removeLoadingFooter();
        isLoading = false;

        mAdapter.addAll(movies);

        if (currentPage != totalPages) {
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
        mPresenter.loadInitialPage(mGenre.getStringId(), currentPage);
    }

    public void loadNextPage() {
        mPresenter.loadNextPage(mGenre.getStringId(), currentPage);
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
