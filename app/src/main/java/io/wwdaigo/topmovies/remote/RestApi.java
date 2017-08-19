package io.wwdaigo.topmovies.remote;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.wwdaigo.topmovies.BuildConfig;
import io.wwdaigo.topmovies.commons.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.wwdaigo.topmovies.commons.Constants.Remote.TIMEOUT;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class RestApi {

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new RestInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private class RestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter(Constants.Remote.API_PARAM, BuildConfig.API_KEY).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}