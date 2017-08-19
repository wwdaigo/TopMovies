package io.wwdaigo.topmovies.remote.manager;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Reusable;
import io.reactivex.Observable;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.remote.RestApi;
import io.wwdaigo.topmovies.remote.request.MoviesRequest;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MoviesManager {

    private MoviesRequest moviesRequest;

    @Inject
    public MoviesManager(MoviesRequest moviesRequest) {
        this.moviesRequest = moviesRequest;
    }

    public Observable<Result<BaseResponse<MovieData>>> getPopular() {
        return moviesRequest.getMoviePopular();
    }

    public Observable<Result<BaseResponse<MovieData>>> getTopRated() {
        return moviesRequest.getMovieTopRated();
    }
}