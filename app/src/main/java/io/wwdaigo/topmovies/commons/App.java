package io.wwdaigo.topmovies.commons;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.wwdaigo.topmovies.dagger.components.DaggerActivityComponent;
import io.wwdaigo.topmovies.dagger.components.DaggerApiComponent;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApiComponent.create().inject(this);
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
