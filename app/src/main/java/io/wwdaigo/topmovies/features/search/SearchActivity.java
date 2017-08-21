package io.wwdaigo.topmovies.features.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.features.details.activities.DetailsActivity;

public class SearchActivity extends AppCompatActivity {

    public static Intent startIntent(Context context) {

        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
