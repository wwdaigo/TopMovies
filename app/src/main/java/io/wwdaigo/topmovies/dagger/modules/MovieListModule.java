package io.wwdaigo.topmovies.dagger.modules;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.wwdaigo.topmovies.features.movielist.activities.MainActivity;
import io.wwdaigo.topmovies.features.movielist.adapters.MovieListAdapter;
import io.wwdaigo.topmovies.features.movielist.fragments.LoadingFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.MovieListFragment;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModel;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.router.MainRouter;
import io.wwdaigo.topmovies.router.MainRouterType;

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

    @Provides
    static MovieListAdapter providesMovieListAdapter() {
        return new MovieListAdapter();
    }

    @Provides
    static MainRouterType providesMainRouter() {
        return new MainRouter();
    }

}
