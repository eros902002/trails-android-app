package com.erostech.trails.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.ui.activities.AboutActivity;
import com.erostech.trails.ui.activities.CreditsActivity;
import com.erostech.trails.ui.activities.LicensesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class MoreFragment extends Fragment {
    public static final String TAG = MoreFragment.class.getSimpleName();

    @BindView(R.id.item_about)
    TextView mAboutItem;
    @BindView(R.id.item_credits)
    TextView mCreditsItem;
    @BindView(R.id.item_licenses)
    TextView mLicensesItem;

    private Unbinder mUnbinder;

    public static MoreFragment newInstance() {

        Bundle args = new Bundle();

        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MoreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAboutItem.setOnClickListener(v -> AboutActivity.start(getContext()));
        mCreditsItem.setOnClickListener(v -> CreditsActivity.start(getContext()));
        mLicensesItem.setOnClickListener(v -> LicensesActivity.start(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
