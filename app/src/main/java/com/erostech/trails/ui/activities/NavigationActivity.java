package com.erostech.trails.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.Window;

import com.erostech.trails.R;
import com.erostech.trails.ui.fragments.GenreListFragment;
import com.erostech.trails.ui.fragments.MainListingFragment;
import com.erostech.trails.ui.fragments.MoreFragment;

public class NavigationActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return changeFragment(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            Transition ts = new Explode();
            ts.setDuration(5000);
            getWindow().setEnterTransition(ts);
            getWindow().setExitTransition(ts);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showSearch();
    }

    private boolean changeFragment(int id) {
        boolean changed = false;

        switch (id) {
            case R.id.navigation_search:
                showSearch();
                changed = true;
                break;
            case R.id.navigation_genre:
                showGenres();
                changed = true;
                break;
            case R.id.navigation_more:
                showMore();
                changed = true;
                break;
        }

        return changed;
    }

    private void showSearch() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MainListingFragment.newInstance())
                .commit();
        setTitle(R.string.title_search);
    }

    private void showGenres() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, GenreListFragment.newInstance())
                .commit();
        setTitle(R.string.title_genres);
    }

    private void showMore() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MoreFragment.newInstance())
                .commit();
        setTitle(R.string.title_more);
    }

}
