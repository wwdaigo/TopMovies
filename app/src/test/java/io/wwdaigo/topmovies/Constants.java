package io.wwdaigo.topmovies;

/**
 * Created by daigomatsuoka on 22/08/17.
 */

public final class Constants {
    final class ResponseCode {
        public static final int OK = 200;
        public static final int BAD_REQUEST = 400;
        public static final int INVALID_TOKEN = 401;
    }

    final class ResponseFile {
        public static final String MOVIE = "movieResponse.json";
        public static final String SEARCH = "searchResponse.json";
    }
}
