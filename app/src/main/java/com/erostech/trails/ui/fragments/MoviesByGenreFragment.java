package com.erostech.trails.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erostech.trails.Constants;
import com.erostech.trails.R;
import com.erostech.trails.adapters.PaginationAdapter;
import com.erostech.trails.core.data.models.Genre;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesByGenreFragment extends Fragment {
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

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private Unbinder mUnbinder;

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
