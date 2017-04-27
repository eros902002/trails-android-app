package com.erostech.trails.core.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

@Module
public class DatabaseModule {
    public DatabaseModule() {

    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
