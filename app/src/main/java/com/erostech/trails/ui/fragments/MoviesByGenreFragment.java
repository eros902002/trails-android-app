package com.erostech.trails.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesByGenreFragment extends Fragment {

    public MoviesByGenreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_by_genre, container, false);
    }
}
