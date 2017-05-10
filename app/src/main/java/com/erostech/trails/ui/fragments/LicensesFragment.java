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

public class LicensesFragment extends Fragment {
    public static final String TAG = LicensesFragment.class.getSimpleName();

    public static LicensesFragment newInstance() {

        Bundle args = new Bundle();

        LicensesFragment fragment = new LicensesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LicensesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_licenses, container, false);
        return view;
    }
}
