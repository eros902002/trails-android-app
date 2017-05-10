package com.erostech.trails.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.erostech.trails.Constants;
import com.erostech.trails.R;
import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.ui.fragments.MoviesByGenreFragment;

public class MoviesByGenreActivity extends AppCompatActivity {

    public static Intent newIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, MoviesByGenreActivity.class);
        intent.putExtra(Constants.ARGUMENT_GENRE, genre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_genre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().hasExtra(Constants.ARGUMENT_GENRE)) {
            Genre genre = getIntent().getParcelableExtra(Constants.ARGUMENT_GENRE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, MoviesByGenreFragment.newInstance(genre))
                    .commit();
        } else {
            finish();
        }
    }

}
