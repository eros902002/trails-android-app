package com.erostech.trails.core.component;

import com.erostech.trails.core.module.AppModule;
import com.erostech.trails.core.module.DatabaseModule;
import com.erostech.trails.core.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DatabaseModule.class})
public interface AppComponent {
    Retrofit retrofit();
    Realm realm();
}
