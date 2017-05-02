package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.ui.activities.MoviesByGenreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.genre_name)
    TextView genreName;
    Genre mGenre;

    public GenreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Genre genre) {
        mGenre = genre;
        if (mGenre != null) {
            genreName.setText(mGenre.getName());
            itemView.setOnClickListener(this);
        } else {
            itemView.setOnClickListener(null);
        }
    }

    @Override
    public void onClick(View v) {
        itemView.getContext().startActivity(
                MoviesByGenreActivity.newIntent(itemView.getContext(), mGenre));
    }
}
