package io.wwdaigo.topmovies.features.movielist.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.features.movielist.fragments.MovieListFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.OnMovieListFragmentInteraction;

public class MainActivity extends Activity implements HasFragmentInjector, OnMovieListFragmentInteraction {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment_container, MovieListFragment.newInstance());
        transaction.commit();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}
