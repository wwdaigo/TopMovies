package io.wwdaigo.topmovies.di.components;

import javax.inject.Singleton;

import dagger.Component;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.di.modules.AppModule;
import io.wwdaigo.topmovies.di.modules.MainActivityModule;
import io.wwdaigo.topmovies.di.modules.RemoteModule;
import io.wwdaigo.topmovies.features.details.activities.DetailsActivity;
import io.wwdaigo.topmovies.features.movielist.activities.MainActivity;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Component(modules = {
        AppModule.class,
        MainActivityModule.class,
        RemoteModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(DetailsActivity activity);
}
