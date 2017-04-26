package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.erostech.trails.R;
import com.erostech.trails.config.Config;
import com.erostech.trails.core.data.models.Movie;

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
    TextView year;
    @BindView(R.id.movie_poster)
    ImageView posterImg;
    @BindView(R.id.movie_progress)
    ProgressBar progress;

    public MovieViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        if (movie != null) {
            movieTitle.setText(movie.getTitle());
            year.setText(movie.getReleaseDate().substring(0, 4) + " | " + movie.getOriginalLanguage().toUpperCase());
            movieDesc.setText(movie.getOverview());

            Glide.with(itemView.getContext())
                    .load(Config.MOVIES_IMAGES_BASE_URL + movie.getPosterPath())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .into(posterImg);
        }
    }
}
