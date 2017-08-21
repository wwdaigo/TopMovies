package io.wwdaigo.topmovies.di.modules;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.di.scopes.ActivityScope;
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
public abstract class MainActivityModule {

    @Provides
    static MovieListViewModelType providesMovieListViewModel(MoviesManager moviesManager, PreferencesManagerType preferencesManager) {
        return new MovieListViewModel(moviesManager, preferencesManager);
    }

    @Provides
    static MovieListAdapter providesMovieListAdapter() {
        return new MovieListAdapter();
    }

    @Provides
    static MainRouterType providesMainRouter() {
        return new MainRouter();
    }

}
