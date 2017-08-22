package io.wwdaigo.topmovies.di.modules;

import dagger.Module;
import dagger.Provides;
import io.wwdaigo.topmovies.BuildConfig;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;
import okhttp3.HttpUrl;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

@Module
public class RemoteModule {

    @Provides
    HttpUrl providesHttpUrl() {
        return HttpUrl.parse(BuildConfig.SERVER_URL);
    }

    @Provides
    RestApi providesRestApi(HttpUrl httpUrl) {
        return new RestApi(httpUrl);
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