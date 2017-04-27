package com.erostech.trails.ui.components;

import com.erostech.trails.core.component.AppComponent;
import com.erostech.trails.ui.modules.MainListingModule;
import com.erostech.trails.ui.fragments.MainListingFragment;
import com.erostech.trails.util.CustomScope;

import dagger.Component;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@CustomScope
@Component(dependencies = AppComponent.class, modules = MainListingModule.class)
public interface MainListingComponent {
    void inject(MainListingFragment fragment);
}
