package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Genre;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.genre_name)
    TextView genreName;

    public GenreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Genre genre) {
        if (genre != null) {
            genreName.setText(genre.getName());
        }
    }
}
