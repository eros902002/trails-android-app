package com.erostech.trails.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;

/**
 * Created by erosgarciaponte on 10/05/2017.
 */

public class CreditsFragment extends Fragment {
    public static final String TAG = CreditsFragment.class.getSimpleName();

    public static CreditsFragment newInstance() {

        Bundle args = new Bundle();

        CreditsFragment fragment = new CreditsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CreditsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        return view;
    }
}
