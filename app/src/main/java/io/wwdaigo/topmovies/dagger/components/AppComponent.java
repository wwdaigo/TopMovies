package io.wwdaigo.topmovies.dagger.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.dagger.modules.ActivityModule;
import io.wwdaigo.topmovies.dagger.modules.ApiModule;
import io.wwdaigo.topmovies.dagger.modules.ManagerModule;
import io.wwdaigo.topmovies.dagger.modules.ViewModelModule;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        ApiModule.class,
        ManagerModule.class,
        ViewModelModule.class})
public interface AppComponent extends AndroidInjector<App> {

}
