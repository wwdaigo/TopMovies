package io.wwdaigo.topmovies.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.App;
import io.wwdaigo.topmovies.preferences.PreferencesManager;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;
import io.wwdaigo.topmovies.router.MainRouter;
import io.wwdaigo.topmovies.router.MainRouterType;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(App app) {
        this.context = app;
    }

    @Provides
    Context providesContext() {
        return this.context;
    }

    @Provides
    static PreferencesManagerType providesPreferencesManager(Context context) {
        return new PreferencesManager(context);
    }

    @Provides
    MainRouterType providesMainRouter() {
        return new MainRouter();
    }
}
