package com.erostech.trails.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.ui.viewholders.CountryViewHolder;

import java.util.List;

/**
 * Created by erosgarciaponte on 19/04/2017.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    private List<Country> mCountries;

    public CountriesAdapter(List<Country> countries) {
        mCountries = countries;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.bind(mCountries.get(position));
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }
}
