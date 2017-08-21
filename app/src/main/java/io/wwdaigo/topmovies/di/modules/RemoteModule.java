package io.wwdaigo.topmovies.di.modules;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.di.scopes.ActivityScope;
import io.wwdaigo.topmovies.di.scopes.ApplicationScope;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

@Module
public class RemoteModule {

    @Provides
    RestApi providesRestApi() {
        return new RestApi();
    }

    @Provides
    MoviesRequest providesMoviesRequest(RestApi restApi) {
        return restApi.getMovieRequest();
    }

    @Provides
    MoviesManager providesMoviesManager(MoviesRequest request) {
        return new MoviesManager(request);
    }
}