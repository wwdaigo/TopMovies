package io.wwdaigo.topmovies.dagger.modules;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.wwdaigo.topmovies.features.movielist.activities.MainActivity;
import io.wwdaigo.topmovies.features.movielist.fragments.MovieListFragment;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModel;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Module
public abstract class MovieListModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();

    @ContributesAndroidInjector
    abstract MovieListFragment movieListFragmentInjector();

    @Provides
    static MovieListViewModelType providesMovieListViewModel(MoviesManager moviesManager) {
        return new MovieListViewModel(moviesManager);
    }
}
