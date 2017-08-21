package io.wwdaigo.topmovies.features.movielist.viewmodels;

import android.content.Context;
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
import io.wwdaigo.topmovies.commons.IntPrefsKeys;
import io.wwdaigo.topmovies.commons.viewmodels.ViewModel;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MovieListViewModel extends ViewModel implements MovieListViewModelType, MovieListViewModelInputs, MovieListViewModelOutputs {

    private PreferencesManagerType preferencesManager;
    private MoviesManager moviesManager;

    private PublishSubject<List<MovieData>> listMovieDataPublish;
    private Observable<List<MovieData>> listMovieData;

    private PublishSubject<Integer> titleStringResourcePublish;
    private Observable<Integer> titleStringResource;

    public MovieListViewModel(MoviesManager moviesManager, PreferencesManagerType preferencesManager) {

        this.moviesManager = moviesManager;
        this.preferencesManager = preferencesManager;

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
    public void loadSavedOption() {
        int option = preferencesManager.loadInt(IntPrefsKeys.SELECTED_LIST);
        switch (option) {
            case R.id.menu_main_popular:
                loadMostPopularMovies();
                break;

            case R.id.menu_main_top_rated:
                loadTopRatedMovies();
                break;
        }
    }

    @Override
    public void saveOption(int option) {
        preferencesManager.saveInt(IntPrefsKeys.SELECTED_LIST, option);
    }

    @Override
    public void loadTopRatedMovies() {
        isLoadingPublish.onNext(true);

        moviesManager.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<BaseResponse<MovieData>>>() {
                    @Override
                    public void accept(@NonNull Result<BaseResponse<MovieData>> baseResponseResult) throws Exception {
                        titleStringResourcePublish.onNext(R.string.top_rated_movies);
                        handleResponse(baseResponseResult);
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
                        titleStringResourcePublish.onNext(R.string.popular_movies);
                        handleResponse(baseResponseResult);
                    }
                });
    }

    private void handleResponse(Result<BaseResponse<MovieData>> baseResponseResult) {
        isLoadingPublish.onNext(false);

        if (baseResponseResult.error() != null || !baseResponseResult.response().isSuccessful()) {
            MovieListViewModel.this.hasErrorPublish.onNext(true);
        } else {
            MovieListViewModel.this.hasErrorPublish.onNext(false);
            MovieListViewModel.this.listMovieDataPublish.onNext(
                    baseResponseResult.response().body().getResults()
            );
        }
    }

    /* OUTPUTS */

    @Override
    public Observable<List<MovieData>> listMovieData() {
        return listMovieData;
    }

    @Override
    public Observable<Integer> getTitleStringResource() {
        return titleStringResource;
    }
}
