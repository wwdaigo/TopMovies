package io.wwdaigo.topmovies.features.details.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import dagger.android.AndroidInjection;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.ActivityDetailsBinding;
import io.wwdaigo.topmovies.features.details.fragments.DetailsFragment;

import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.DETAILS_FRAGMENT_TAG;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE_DATA = "extraMovieData";

    private ActivityDetailsBinding binding;
    private MovieData movieData;

    public static Intent startIntent(Context context, MovieData movieData) {

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_DATA, movieData);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        getIntentExtas();
        bindToolBar();
        setupBackdropImage();
        initFragment();
    }

    private void getIntentExtas() {
        Intent intent = getIntent();
        movieData = (MovieData) intent.getSerializableExtra(EXTRA_MOVIE_DATA);
    }

    private void bindToolBar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.collapsingToolbar.setTitle(movieData.getTitle());
        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void setupBackdropImage() {
        Picasso.with(this)
                .load(movieData.getBackdropPath())
                .into(binding.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        binding.image.setImageResource(R.drawable.error);
                    }
                });
    }

    private void initFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.details_fragment_container, DetailsFragment.newInstance(movieData), DETAILS_FRAGMENT_TAG);
        transaction.commit();
    }
}