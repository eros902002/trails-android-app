package com.erostech.trails.ui.components;

import com.erostech.trails.core.component.AppComponent;
import com.erostech.trails.ui.fragments.GenreListFragment;
import com.erostech.trails.ui.modules.GenreListModule;
import com.erostech.trails.util.CustomScope;

import dagger.Component;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

@CustomScope
@Component(dependencies = AppComponent.class, modules = GenreListModule.class)
public interface GenreListComponent {
    void inject(GenreListFragment fragment);
}
