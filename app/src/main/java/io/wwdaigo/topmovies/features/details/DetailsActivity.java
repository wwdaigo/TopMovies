package io.wwdaigo.topmovies.features.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;

public class DetailsActivity extends Activity {

    public static Intent startIntent(Context context, MovieData movieData) {

        final String EXTRA_MOVIE_DATA = "extraMovieData";

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_DATA, movieData);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
