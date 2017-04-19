package com.erostech.trails.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Province;
import com.erostech.trails.ui.viewholders.ProvinceViewHolder;

import java.util.List;

/**
 * Created by erosgarciaponte on 19/04/2017.
 */

public class ProvincesAdapter extends RecyclerView.Adapter<ProvinceViewHolder> {
    private List<Province> mProvinces;

    public ProvincesAdapter(List<Province> provinces) {
        mProvinces = provinces;
    }

    @Override
    public ProvinceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_province, parent, false);
        ProvinceViewHolder provinceViewHolder = new ProvinceViewHolder(view);
        return provinceViewHolder;
    }

    @Override
    public void onBindViewHolder(ProvinceViewHolder holder, int position) {
        holder.bind(mProvinces.get(position));
    }

    @Override
    public int getItemCount() {
        return mProvinces.size();
    }
}
