package io.wwdaigo.topmovies.features.movielist.viewmodels;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MovieListViewModel implements MovieListViewModelType, MovieListViewModelInputs, MovieListViewModelOutputs {

    private PublishSubject<Boolean> isLoadingPublish;
    private Observable<Boolean> isLoading;
    private Observable<MovieData> movieData;

    public MovieListViewModel() {
        isLoadingPublish = PublishSubject.create();
        isLoading = isLoadingPublish;

        movieData = PublishSubject.create();
    }

    @Override
    public MovieListViewModelInputs getInputs() {
        return this;
    }

    @Override
    public MovieListViewModelOutputs getOutputs() {
        return this;
    }

    /* INPUTS */

    @Override
    public void loadMovies() {
        isLoadingPublish.onNext(true);
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
