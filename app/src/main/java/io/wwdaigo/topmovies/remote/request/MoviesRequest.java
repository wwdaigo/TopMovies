package io.wwdaigo.topmovies.remote.request;

import io.reactivex.Observable;
import io.wwdaigo.topmovies.data.BaseResponse;
import io.wwdaigo.topmovies.data.MovieData;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public interface MoviesRequest {

    @GET("movie/popular")
    Observable<Result<BaseResponse<MovieData>>> getMoviePopular();

    @GET("movie/top_rated")
    Observable<Result<BaseResponse<MovieData>>> getMovieTopRated();
}
