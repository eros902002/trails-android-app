package com.erostech.trails.ui.modules;

import com.erostech.trails.ui.contracts.MoviesByGenreContract;
import com.erostech.trails.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erosgarciaponte on 10/05/2017.
 */

@Module
public class MoviesByGenreModule {
    private final MoviesByGenreContract.View mView;

    public MoviesByGenreModule(MoviesByGenreContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    MoviesByGenreContract.View providesMainScreenContractView() {
        return mView;
    }
}
