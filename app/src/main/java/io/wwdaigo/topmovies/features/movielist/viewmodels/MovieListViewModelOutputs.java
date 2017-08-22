package io.wwdaigo.topmovies.features.movielist.viewmodels;

import java.util.List;

import io.reactivex.Observable;
import io.wwdaigo.topmovies.commons.viewmodels.ViewModelOutputs;
import io.wwdaigo.topmovies.data.MovieData;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public interface MovieListViewModelOutputs extends ViewModelOutputs{
    Observable<List<MovieData>> listMovieData();
    Observable<Integer> getTitleStringResource();
}
