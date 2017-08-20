package io.wwdaigo.topmovies.dagger.components;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.dagger.modules.AppModule;
import io.wwdaigo.topmovies.dagger.modules.MovieListModule;
import io.wwdaigo.topmovies.dagger.modules.RemoteModule;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MovieListModule.class,
        RemoteModule.class})
public interface AppComponent extends AndroidInjector<App> {
}
