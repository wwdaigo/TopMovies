package io.wwdaigo.topmovies.router;

import android.content.Context;

import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public interface MainRouterType {
    void openMovie(Context context, MovieData movieData);
}
