package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Country;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 19/04/2017.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.country_flag)
    ImageView mFlagView;
    @BindView(R.id.country_name)
    TextView mNameView;

    private Country mCountry;

    public CountryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Country country) {
        mCountry = country;

        if (country.getName() != null) {
            mNameView.setText(country.getName());
        }

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
