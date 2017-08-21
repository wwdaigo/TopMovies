package io.wwdaigo.topmovies.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.commons.App;
import io.wwdaigo.topmovies.preferences.PreferencesManager;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;

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
    static PreferencesManagerType providesPreferencesManager() {
        return new PreferencesManager();
    }
}
