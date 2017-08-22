package io.wwdaigo.topmovies.commons.viewmodels;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

public class ViewModel implements ViewModelOutputs {

    protected PublishSubject<Boolean> isLoadingPublish;
    protected Observable<Boolean> isLoading;

    protected PublishSubject<Boolean> hasErrorPublish;
    protected Observable<Boolean> hasError;

    public ViewModel() {
        isLoadingPublish = PublishSubject.create();
        isLoading = isLoadingPublish;

        hasErrorPublish = PublishSubject.create();
        hasError = hasErrorPublish;
    }

    @Override
    public Observable<Boolean> isLoading() {
        return isLoading;
    }

    @Override
    public Observable<Boolean> hasError() {
        return hasError;
    }
}
