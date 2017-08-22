package io.wwdaigo.topmovies;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.manager.MoviesManager;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;
import io.wwdaigo.topmovies.utils.Constants;
import io.wwdaigo.topmovies.utils.JsonLoader;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.adapter.rxjava2.Result;

import static io.wwdaigo.topmovies.utils.Constants.ResponseCode.OK;
import static io.wwdaigo.topmovies.utils.Constants.ResponseFile.MOVIE;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void testTopRatedMoviesRequest() throws Exception {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(OK);
        mockResponse.setBody(new JsonLoader().loadJson(MOVIE));
        server.enqueue(mockResponse);

        moviesManager.getTopRated().subscribe(testSub);

        // Should have no error
        testSub.assertNoErrors();
        testSub.assertNoTimeout();
        testSub.assertSubscribed();

        // Assert Data
        testSub.assertOf(new Consumer<TestObserver<Result<BaseResponse<MovieData>>>>() {
            @Override
            public void accept(@NonNull TestObserver<Result<BaseResponse<MovieData>>> resultTestObserver) throws Exception {
                assertResults(resultTestObserver);
            }
        });
    }

    @Test
    public void testPopularMoviesRequest() throws Exception {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(OK);
        mockResponse.setBody(new JsonLoader().loadJson(MOVIE));
        server.enqueue(mockResponse);

        moviesManager.getPopular().subscribe(testSub);

        // Should have no error
        testSub.assertNoErrors();
        testSub.assertNoTimeout();
        testSub.assertSubscribed();

        // Assert Data
        testSub.assertOf(new Consumer<TestObserver<Result<BaseResponse<MovieData>>>>() {
            @Override
            public void accept(@NonNull TestObserver<Result<BaseResponse<MovieData>>> resultTestObserver) throws Exception {
                assertResults(resultTestObserver);
            }
        });
    }

    @Test
    public void testSearchRequest() throws Exception {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(OK);
        mockResponse.setBody(new JsonLoader().loadJson(MOVIE));
        server.enqueue(mockResponse);

        moviesManager.searchMovie("").subscribe(testSub);

        // Should have no error
        testSub.assertNoErrors();
        testSub.assertNoTimeout();
        testSub.assertSubscribed();

        // Assert Data
        testSub.assertOf(new Consumer<TestObserver<Result<BaseResponse<MovieData>>>>() {
            @Override
            public void accept(@NonNull TestObserver<Result<BaseResponse<MovieData>>> resultTestObserver) throws Exception {
                assertResults(resultTestObserver);
            }
        });
    }

    private void assertResults(TestObserver<Result<BaseResponse<MovieData>>> resultTestObserver) {

        final float delta = 0.01f;

        // Expected Data
        final int page = 1;
        final int totalResults = 19585;
        final int totalPages = 980;
        final int countResult = 20;

        // Second result
        final int voteCount = 4670;
        final int id = 321612;
        final float voteAverage = 6.8f;
        final String title = "Beauty and the Beast";
        final float popularity = 115.32398f;
        final String originalTitle = "Beauty and the Beast";
        final String overview = "A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.";
        final String releaseDate = "2017-03-16";

        BaseResponse<MovieData> body = resultTestObserver.values().get(0).response().body();

        assertEquals(page, body.getPage());
        assertEquals(totalPages, body.getTotalPages());
        assertEquals(totalResults, body.getTotalResults());

        assertEquals(countResult, body.getResults().size());

        // Assert Second result
        MovieData movieData = body.getResults().get(1);

        assertEquals(voteCount, movieData.getVoteCount());
        assertEquals(id, movieData.getId());
        assertEquals(voteAverage, movieData.getVoteAverage(), delta);
        assertEquals(title, movieData.getTitle());
        assertEquals(originalTitle, movieData.getOriginalTitle());
        assertEquals(popularity, movieData.getPopularity(), delta);
        assertEquals(releaseDate, movieData.getReleaseDate());
        assertEquals(overview, movieData.getOverview());
    }
}