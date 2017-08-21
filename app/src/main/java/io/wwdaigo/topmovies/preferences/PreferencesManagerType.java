package io.wwdaigo.topmovies.preferences;

import android.content.Context;

import dagger.multibindings.IntKey;
import io.wwdaigo.topmovies.commons.IntPrefsKeys;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public interface PreferencesManagerType {

    void saveInt(IntPrefsKeys key, int value);
    int loadInt(IntPrefsKeys key);
}
