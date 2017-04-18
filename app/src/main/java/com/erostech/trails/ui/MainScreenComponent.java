package com.erostech.trails.ui;

import com.erostech.trails.NetComponent;
import com.erostech.trails.util.CustomScope;

import dagger.Component;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivityFragment fragment);
}
