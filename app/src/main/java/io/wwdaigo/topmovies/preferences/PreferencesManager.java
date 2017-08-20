package io.wwdaigo.topmovies.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import io.wwdaigo.topmovies.commons.IntPrefsKeys;

import static io.wwdaigo.topmovies.commons.Constants.Preferences.PREFERENCES_KEY;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public class PreferencesManager implements PreferencesManagerType {

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void saveInt(Context context, IntPrefsKeys key, int value) {
        getSharedPreferences(context)
                .edit()
                .putInt(key.getKey(), value)
                .apply();
    }

    @Override
    public int loadInt(Context context, IntPrefsKeys key) {
        return getSharedPreferences(context)
                .getInt(key.getKey(), key.getDefaultValue());
    }
}
