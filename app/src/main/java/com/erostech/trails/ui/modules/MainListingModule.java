package com.erostech.trails.ui.modules;

import com.erostech.trails.ui.contracts.MainListingContract;
import com.erostech.trails.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@Module
public class MainListingModule {
    private final MainListingContract.View mView;

    public MainListingModule(MainListingContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    MainListingContract.View providesMainScreenContractView() {
        return mView;
    }
}
