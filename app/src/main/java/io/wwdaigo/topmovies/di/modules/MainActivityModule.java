package io.wwdaigo.topmovies.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.wwdaigo.topmovies.features.movielist.adapters.MovieListAdapter;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModel;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Module
public class MainActivityModule {

    @Provides
    MovieListViewModelType providesMovieListViewModel(MoviesManager moviesManager, PreferencesManagerType preferencesManager) {
        return new MovieListViewModel(moviesManager, preferencesManager);
    }

    @Provides
    MovieListAdapter providesMovieListAdapter() {
        return new MovieListAdapter();
    }

    @Provides
    @Named("mainActivityCompositeDisposable")
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

}
