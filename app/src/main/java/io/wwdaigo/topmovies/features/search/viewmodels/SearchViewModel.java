package io.wwdaigo.topmovies.features.search.viewmodels;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.wwdaigo.topmovies.commons.viewmodels.ViewModel;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by daigomatsuoka on 21/08/17.
 */

public class SearchViewModel extends ViewModel implements SearchViewModelType, SearchViewModelInputs, SearchViewModelOutputs {

    private MoviesManager moviesManager;

    private PublishSubject<List<MovieData>> searchResultPublish;
    private Observable<List<MovieData>> searchResult;

    @Override
    public SearchViewModelInputs getInputs() {
        return this;
    }

    @Override
    public SearchViewModelOutputs getOutputs() {
        return this;
    }

    public SearchViewModel(MoviesManager moviesManager) {
        super();
        this.moviesManager = moviesManager;

        searchResultPublish = PublishSubject.create();
        searchResult = searchResultPublish;
    }

    @Override
    public void search(String keyWord) {
        isLoadingPublish.onNext(true);

        moviesManager.searchMovie(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<BaseResponse<MovieData>>>() {
                    @Override
                    public void accept(@NonNull Result<BaseResponse<MovieData>> baseResponseResult) throws Exception {

                        isLoadingPublish.onNext(false);

                        if (baseResponseResult.error() != null || !baseResponseResult.response().isSuccessful()) {
                            SearchViewModel.this.hasErrorPublish.onNext(true);
                        } else {
                            SearchViewModel.this.hasErrorPublish.onNext(false);
                            SearchViewModel.this.searchResultPublish.onNext(
                                    baseResponseResult.response().body().getResults()
                            );
                        }
                    }
                });
    }

    @Override
    public Observable<List<MovieData>> searchResult() {
        return searchResult;
    }
}
