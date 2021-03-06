package io.wwdaigo.topmovies.features.movielist.viewmodels;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public interface MovieListViewModelInputs {
    void loadSavedOption();
    void saveOption(int option);

    void loadTopRatedMovies();
    void loadMostPopularMovies();
}
