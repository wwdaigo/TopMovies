package io.wwdaigo.topmovies.commons.enums;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public enum DateFormats {
    MM_DD_YYYY ("MM/dd/yyyy"),
    YEAR("yyyy");

    private String format;
    DateFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
