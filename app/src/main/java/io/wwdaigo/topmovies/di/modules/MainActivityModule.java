package io.wwdaigo.topmovies.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.disposables.CompositeDisposable;
import io.wwdaigo.topmovies.features.movielist.activities.MainActivity;
import io.wwdaigo.topmovies.features.movielist.adapters.MovieListAdapter;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModel;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.preferences.PreferencesManagerType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.router.MainRouter;
import io.wwdaigo.topmovies.router.MainRouterType;

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
