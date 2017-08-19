package io.wwdaigo.topmovies.dagger.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.dagger.modules.ActivityModule;
import io.wwdaigo.topmovies.dagger.modules.ApiModule;
import io.wwdaigo.topmovies.dagger.modules.ManagerModule;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        ApiModule.class,
        ManagerModule.class})
public interface AppComponent extends AndroidInjector<App> {
}
