package io.wwdaigo.topmovies.commons.viewmodels;

import io.reactivex.Observable;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

public interface ViewModelOutputs {
    Observable<Boolean> isLoading();
    Observable<Boolean> hasError();
}
