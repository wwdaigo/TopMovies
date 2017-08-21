package io.wwdaigo.topmovies.commons.enums;

import io.wwdaigo.topmovies.R;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public enum IntPrefsKeys {
    SELECTED_LIST("selectedList", R.id.menu_main_popular);

    private String mKey;
    private int mDefaultValue;

    IntPrefsKeys(String key, int defaultValue) {
        mKey = key;
        mDefaultValue = defaultValue;
    }

    public int getDefaultValue() {
        return mDefaultValue;
    }

    public String getKey() {
        return mKey;
    }
}
