package io.wwdaigo.topmovies.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.wwdaigo.topmovies.features.search.viewmodels.SearchViewModel;
import io.wwdaigo.topmovies.features.search.viewmodels.SearchViewModelType;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

@Module
public class SearchActivityModule {

    @Provides
    SearchViewModelType providesMovieListViewModel(MoviesManager moviesManager) {
        return new SearchViewModel(moviesManager);
    }

    @Provides
    @Named("searchActivityCompositeDisposable")
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }
}
