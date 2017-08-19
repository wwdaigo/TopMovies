package io.wwdaigo.topmovies.dagger.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

@Module
public abstract class ApiModule {

    @Provides
    static RestApi providesRestApi() {
        return new RestApi();
    }

    @Provides
    static MoviesRequest providesMoviesRequest(RestApi restApi) {
        return restApi.getMovieRequest();
    }
}