package com.erostech.trails;

import android.app.Application;

import com.erostech.trails.config.Config;
import com.erostech.trails.core.component.DaggerAppComponent;
import com.erostech.trails.core.component.AppComponent;
import com.erostech.trails.core.module.AppModule;
import com.erostech.trails.core.module.DatabaseModule;
import com.erostech.trails.core.module.NetModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Config.MOVIES_API_URL))
                .databaseModule(new DatabaseModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
