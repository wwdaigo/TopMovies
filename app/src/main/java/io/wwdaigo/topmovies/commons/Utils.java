package io.wwdaigo.topmovies.commons;

import android.icu.text.SimpleDateFormat;

import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public final class Utils {

    private static SimpleDateFormat getSimpleDateFormat(DateFormats format) {
        return new SimpleDateFormat(format.getFormat(), Locale.getDefault());
    }
    public static String dateToString(Date date, DateFormats format) {
        return getSimpleDateFormat(format).format(date);
    }
}