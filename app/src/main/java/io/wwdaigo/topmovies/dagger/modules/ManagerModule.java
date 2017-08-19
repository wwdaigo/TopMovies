package io.wwdaigo.topmovies.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Module
public abstract class ManagerModule {

    @Provides
    static MoviesManager providesMoviesManager(MoviesRequest request) {
        return new MoviesManager(request);
    }
}
