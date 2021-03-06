package io.wwdaigo.topmovies.router;

import android.content.Context;

import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.features.details.activities.DetailsActivity;
import io.wwdaigo.topmovies.features.search.activities.SearchActivity;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public final class MainRouter implements MainRouterType {

    @Override
    public void openMovie(Context context, MovieData movieData) {
        context.startActivity(DetailsActivity.startIntent(context, movieData));
    }

    @Override
    public void openSearch(Context context) {
        context.startActivity(SearchActivity.startIntent(context));
    }
}
