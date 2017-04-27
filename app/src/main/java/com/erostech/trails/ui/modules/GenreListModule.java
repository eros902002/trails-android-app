package com.erostech.trails.ui.modules;

import com.erostech.trails.ui.contracts.GenreListContract;
import com.erostech.trails.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

@Module
public class GenreListModule {
    private final GenreListContract.View mView;

    public GenreListModule(GenreListContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    GenreListContract.View provideGenreListContractView() {
        return mView;
    }
}
