package io.wwdaigo.topmovies.commons;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public final class Constants {

    public final class Preferences {
        public static final String PREFERENCES_KEY = "topMoviesPreferences";
    }

    public final class Remote {
        public static final String API_PARAM = "api_key";
        public static final int TIMEOUT = 60;
    }

    public final class Image {
        public static final String IMAGE_URL = "http://image.tmdb.org/t/p/";
        public static final String COVER_WIDTH = "w780";
    }

    public final class FragmentTags {
        public static final String LOADING_FRAGMENT_TAG = "loadingFragmentTag";
        public static final String ERROR_FRAGMENT_TAG = "errorFragmentTag";
        public static final String DETAILS_FRAGMENT_TAG = "detailsFragmentTag";
    }

}