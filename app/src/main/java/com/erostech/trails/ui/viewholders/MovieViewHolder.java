package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erostech.trails.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 19/4/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_desc)
    TextView movieDesc;
    @BindView(R.id.movie_year)
    TextView mYear;
    @BindView(R.id.movie_poster)
    ImageView posterImg;
    @BindView(R.id.movie_progress)
    ProgressBar progress;

    public MovieViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
