package io.wwdaigo.topmovies.features.movielist.viewmodels;

import android.content.Context;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public interface MovieListViewModelInputs {
    void loadSavedOption(Context context);
    void saveOption(Context context, int option);

    void loadTopRatedMovies();
    void loadMostPopularMovies();
}
