package io.wwdaigo.topmovies.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModel;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Module
public abstract class ViewModelModule {

    @Provides
    static MovieListViewModelType providesMovieListViewModel() {
        return new MovieListViewModel();
    }
}
