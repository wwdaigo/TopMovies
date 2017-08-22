package io.wwdaigo.topmovies;

import android.graphics.Movie;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.adapter.rxjava2.Result;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by daigomatsuoka on 22/08/17.
 */

public class RemoteManagerTest {

    private MockWebServer server;
    private TestObserver<Result<BaseResponse<MovieData>>> testSub;
    private HttpUrl serverUrl;
    private MoviesRequest moviesRequest;
    private MoviesManager moviesManager;

    @Before
    public void setup() throws Exception {
        server = new MockWebServer();
        server.start();

        serverUrl = server.url("");
        RestApi restApi = new RestApi(serverUrl);
        moviesRequest = restApi.getMovieRequest();
        moviesManager = new MoviesManager(moviesRequest);

        testSub = new TestObserver<>();
    }

    @Test
    public void testAllSetAsNotNull() throws Exception {
        assertNotNull(server);
        assertNotNull(testSub);
        assertNotNull(serverUrl);
        assertNotNull(moviesRequest);
        assertNotNull(moviesManager);
    }
}