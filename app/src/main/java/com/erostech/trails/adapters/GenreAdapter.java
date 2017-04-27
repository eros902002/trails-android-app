package com.erostech.trails.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.ui.viewholders.GenreViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreViewHolder> {

    private List<Genre> mGenres;

    public GenreAdapter() {
        mGenres = new ArrayList<>();
    }


    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        GenreViewHolder viewHolder = new GenreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public Genre getItem(int position) {
        return mGenres.get(position);
    }

    public void add(Genre genre) {
        mGenres.add(genre);
        notifyItemInserted(mGenres.size() -1);
    }

    public void addAll(List<Genre> genres) {
        for (Genre g: genres) {
            add(g);
        }
    }

    public void remove(Genre genre) {
        int position = mGenres.indexOf(genre);
        if (position > -1) {
            mGenres.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }
}
