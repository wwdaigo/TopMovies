package io.wwdaigo.topmovies.dagger.modules;

import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.preferences.PreferencesManager;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

@Module
public class AppModule {

    @Provides
    static PreferencesManagerType providesPreferencesManager() {
        return new PreferencesManager();
    }
}
