package com.erostech.trails.ui.components;

import com.erostech.trails.core.component.NetComponent;
import com.erostech.trails.ui.modules.MainScreenModule;
import com.erostech.trails.ui.fragments.MainActivityFragment;
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
