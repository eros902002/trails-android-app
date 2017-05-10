package com.erostech.trails.ui.components;

import com.erostech.trails.core.component.AppComponent;
import com.erostech.trails.ui.fragments.MoviesByGenreFragment;
import com.erostech.trails.ui.modules.MoviesByGenreModule;
import com.erostech.trails.util.CustomScope;

import dagger.Component;

/**
 * Created by erosgarciaponte on 10/05/2017.
 */

@CustomScope
@Component(dependencies = AppComponent.class, modules = MoviesByGenreModule.class)
public interface MoviesByGenreComponent {
    void inject(MoviesByGenreFragment fragment);
}
