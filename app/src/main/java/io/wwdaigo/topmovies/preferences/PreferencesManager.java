package io.wwdaigo.topmovies.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import io.wwdaigo.topmovies.commons.enums.IntPrefsKeys;

import static io.wwdaigo.topmovies.commons.Constants.Preferences.PREFERENCES_KEY;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public class PreferencesManager implements PreferencesManagerType {

    private Context context;

    public PreferencesManager(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void saveInt(IntPrefsKeys key, int value) {
        getSharedPreferences()
                .edit()
                .putInt(key.getKey(), value)
                .apply();
    }

    @Override
    public int loadInt(IntPrefsKeys key) {
        return getSharedPreferences()
                .getInt(key.getKey(), key.getDefaultValue());
    }
}
