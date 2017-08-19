package io.wwdaigo.topmovies.features.movielist.viewmodels;

import io.reactivex.Observable;
import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public interface MovieListViewModelOutputs {
    Observable<Boolean> isLoading();
    Observable<MovieData> getMovieData();
}