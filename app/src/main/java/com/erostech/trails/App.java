package com.erostech.trails;

import android.app.Application;

import com.erostech.trails.config.Config;
import com.erostech.trails.core.component.DaggerNetComponent;
import com.erostech.trails.core.component.NetComponent;
import com.erostech.trails.core.module.AppModule;
import com.erostech.trails.core.module.NetModule;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Config.MOVIES_API_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
