package com.erostech.trails.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;
import com.erostech.trails.callbacks.LoadingViewHolderCallback;
import com.erostech.trails.callbacks.PaginationAdapterCallback;
import com.erostech.trails.core.data.models.Movie;
import com.erostech.trails.ui.viewholders.LoadingViewHolder;
import com.erostech.trails.ui.viewholders.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erosgarciaponte on 19/4/17.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LoadingViewHolderCallback {
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Movie> mMovies;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMessage;

    public PaginationAdapter(Context context, PaginationAdapterCallback callback) {
        this.mCallback = callback;
        mMovies = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_movie, parent, false);
        viewHolder = new MovieViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                ((MovieViewHolder) holder).bind(movie);
                break;
            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                 loadingViewHolder.bind(mCallback, this, retryPageLoad, errorMessage);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mMovies.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void add(Movie r) {
        mMovies.add(r);
        notifyItemInserted(mMovies.size() - 1);
    }

    public void addAll(List<Movie> movies) {
        for (Movie movie : movies) {
            add(movie);
        }
    }

    public void remove(Movie movie) {
        int position = mMovies.indexOf(movie);
        if (position > -1) {
            mMovies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mMovies.size() - 1;
        Movie movie = getItem(position);

        if (movie != null) {
            mMovies.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void showRetry(boolean show, @Nullable String errorMessage) {
        retryPageLoad = show;
        notifyItemChanged(mMovies.size() - 1);

        if (errorMessage != null) {
            this.errorMessage = errorMessage;
        }
    }
}
