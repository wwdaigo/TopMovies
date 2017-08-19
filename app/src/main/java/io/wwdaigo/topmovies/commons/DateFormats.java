package io.wwdaigo.topmovies.commons;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public enum DateFormats {
    YYYY_MM_DD ("yyyy-MM-dd"),
    YEAR("yyyy");

    private String format;
    DateFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
