package io.wwdaigo.topmovies.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import io.wwdaigo.topmovies.commons.enums.DateFormats;
import io.wwdaigo.topmovies.commons.Utils;

import static io.wwdaigo.topmovies.commons.Constants.Image.COVER_WIDTH;
import static io.wwdaigo.topmovies.commons.Constants.Image.IMAGE_URL;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public final class MovieData implements Serializable {
    private int id;
    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    private String overview;
    private boolean adult;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private boolean video;
    private float popularity;
    @SerializedName("vote_average")
    private float voteAverage;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("%s%s%s", IMAGE_URL, COVER_WIDTH, posterPath);
    }

    public String getBackdropPath() {
        return String.format("%s%s%s", IMAGE_URL, COVER_WIDTH, backdropPath);
    }

    public float getPopularity() {
        return popularity;
    }

    public String getYear() {
        Date date = parseDate(releaseDate);
        if (date != null)
            return Utils.dateToString(date, DateFormats.YEAR);
        else
            return "N/A";
    }

    public String getFormatedDate() {
        Date date = parseDate(releaseDate);
        if (date != null)
            return Utils.dateToString(date, DateFormats.MM_DD_YYYY);
        else
            return "N/A";
    }

    public String getFormatedVote() {
        return String.format(Locale.getDefault(), "(%d) %.1f/10", voteCount, voteAverage);
    }

    private Date parseDate(String dateStr) {
        return Utils.stringToDate(dateStr, DateFormats.YYYY_MM_DD);
    }
}