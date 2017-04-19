package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Province;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 19/04/2017.
 */

public class ProvinceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.country_flag)
    ImageView mFlagView;
    @BindView(R.id.country_name)
    TextView mNameView;

    private Province mProvince;

    public ProvinceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Province province) {
        mProvince = province;

        if (province.getName() != null) {
            mNameView.setText(province.getName());
        }

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
