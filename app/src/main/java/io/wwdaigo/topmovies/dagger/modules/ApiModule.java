package io.wwdaigo.topmovies.dagger.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.BuildConfig;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;
import okhttp3.HttpUrl;

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
    static MoviesRequest providesMovieRequest(RestApi restApi) {
        return restApi.getRetrofit().create(MoviesRequest.class);
    }
}