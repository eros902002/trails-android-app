package com.erostech.trails.core.component;

import com.erostech.trails.core.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

@Singleton
@Component(modules = DatabaseModule.class)
public interface DatabaseComponent {
    Realm realm();
}
