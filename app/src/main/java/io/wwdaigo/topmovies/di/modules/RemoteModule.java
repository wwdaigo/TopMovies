package io.wwdaigo.topmovies.di.modules;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.di.scopes.ActivityScope;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

@Module
public abstract class RemoteModule {

    @Provides
    @ActivityScope
    static RestApi providesRestApi() {
        return new RestApi();
    }

    @Provides
    @ActivityScope
    static MoviesRequest providesMoviesRequest(RestApi restApi) {
        return restApi.getMovieRequest();
    }

    @Provides
    @ActivityScope
    static MoviesManager providesMoviesManager(MoviesRequest request) {
        return new MoviesManager(request);
    }
}