package io.wwdaigo.topmovies.features.search.viewmodels;

import java.util.List;

import io.reactivex.Observable;
import io.wwdaigo.topmovies.commons.viewmodels.ViewModelOutputs;
import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 21/08/17.
 */

public interface SearchViewModelOutputs extends ViewModelOutputs {
    Observable<List<MovieData>> searchResult();
}
