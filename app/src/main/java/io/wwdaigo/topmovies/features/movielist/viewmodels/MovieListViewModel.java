package io.wwdaigo.topmovies.features.movielist.viewmodels;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MovieListViewModel implements MovieListViewModelType, MovieListViewModelInputs, MovieListViewModelOutputs {

    private Observable<Boolean> isLoading;
    private Observable<MovieData> movieData;

    public MovieListViewModel() {
        isLoading = PublishSubject.create();
        movieData = PublishSubject.create();
    }

    @Override
    public MovieListViewModelInputs getInputs() {
        return this;
    }

    @Override
    public MovieListViewModelInputs getOutputs() {
        return this;
    }

    /* INPUTS */

    @Override
    public void loadMovies() {

    }

    /* OUTPUTS */

    @Override
    public Observable<Boolean> isLoading() {
        return isLoading;
    }

    @Override
    public Observable<MovieData> getMovieData() {
        return movieData;
    }
}
