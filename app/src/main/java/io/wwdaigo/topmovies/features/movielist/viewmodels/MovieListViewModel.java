package io.wwdaigo.topmovies.features.movielist.viewmodels;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MovieListViewModel implements MovieListViewModelType, MovieListViewModelInputs, MovieListViewModelOutputs {

    private MoviesManager moviesManager;

    private PublishSubject<Boolean> isLoadingPublish;
    private Observable<Boolean> isLoading;
    private Observable<MovieData> movieData;

    public MovieListViewModel(MoviesManager moviesManager) {

        this.moviesManager = moviesManager;

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

        moviesManager.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<BaseResponse<MovieData>>>() {
                    @Override
                    public void accept(@NonNull Result<BaseResponse<MovieData>> baseResponseResult) throws Exception {
                        Log.i("RESULT", baseResponseResult.response().toString());
                    }
                });
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
