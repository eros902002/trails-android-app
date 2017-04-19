package com.erostech.trails.core.component;

import com.erostech.trails.core.module.AppModule;
import com.erostech.trails.core.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
}
