package io.wwdaigo.topmovies.dagger.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.dagger.modules.ActivityModule;

/**
 * Created by daigomatsuoka on 18/08/17.
 */
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class})
public interface ActivityComponent extends AndroidInjector<App> {
}
