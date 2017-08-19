package io.wwdaigo.topmovies.features.movielist.viewmodels;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.wwdaigo.topmovies.R;
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

    private PublishSubject<List<MovieData>> listMovieDataPublish;
    private Observable<List<MovieData>> listMovieData;

    private PublishSubject<Integer> titleStringResourcePublish;
    private Observable<Integer> titleStringResource;

    public MovieListViewModel(MoviesManager moviesManager) {

        this.moviesManager = moviesManager;

        isLoadingPublish = PublishSubject.create();
        isLoading = isLoadingPublish;

        listMovieDataPublish = PublishSubject.create();
        listMovieData = listMovieDataPublish;

        titleStringResourcePublish = PublishSubject.create();
        titleStringResource = titleStringResourcePublish;
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
    public void loadTopRatedMovies() {
        isLoadingPublish.onNext(true);

        moviesManager.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<BaseResponse<MovieData>>>() {
                    @Override
                    public void accept(@NonNull Result<BaseResponse<MovieData>> baseResponseResult) throws Exception {

                        isLoadingPublish.onNext(false);
                        titleStringResourcePublish.onNext(R.string.top_rated_movies);

                        MovieListViewModel.this.listMovieDataPublish.onNext(
                                baseResponseResult.response().body().getResults()
                        );
                    }
                });
    }

    @Override
    public void loadMostPopularMovies() {
        isLoadingPublish.onNext(true);

        moviesManager.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<BaseResponse<MovieData>>>() {
                    @Override
                    public void accept(@NonNull Result<BaseResponse<MovieData>> baseResponseResult) throws Exception {

                        isLoadingPublish.onNext(false);
                        titleStringResourcePublish.onNext(R.string.popular_movies);

                        MovieListViewModel.this.listMovieDataPublish.onNext(
                                baseResponseResult.response().body().getResults()
                        );
                    }
                });
    }

    /* OUTPUTS */

    @Override
    public Observable<Boolean> isLoading() {
        return isLoading;
    }

    @Override
    public Observable<List<MovieData>> listMovieData() {
        return listMovieData;
    }

    @Override
    public Observable<Integer> getTitleStringResource() {
        return titleStringResource;
    }
}
