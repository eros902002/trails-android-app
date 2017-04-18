package com.erostech.trails.ui;

import com.erostech.trails.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@Module
public class MainScreenModule {
    private final MainScreenContract.View mView;

    public MainScreenModule(MainScreenContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
