package io.wwdaigo.topmovies.commons;

import android.app.Application;

import io.wwdaigo.topmovies.di.components.AppComponent;
import io.wwdaigo.topmovies.di.components.DaggerAppComponent;
import io.wwdaigo.topmovies.di.modules.AppModule;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
