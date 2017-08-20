package io.wwdaigo.topmovies.features.movielist.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.features.movielist.adapters.OnSelectMovieData;
import io.wwdaigo.topmovies.features.movielist.fragments.ErrorFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.LoadingFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.MovieListFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.OnErrorFragmentInteraction;
import io.wwdaigo.topmovies.features.movielist.fragments.OnMovieListFragmentInteraction;

import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.ERROR_FRAGMENT_TAG;
import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.LOADING_FRAGMENT_TAG;
import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.MOVIES_FRAGMENT_TAG;

public class MainActivity extends Activity implements
        HasFragmentInjector,
        OnMovieListFragmentInteraction,
        OnErrorFragmentInteraction {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, MovieListFragment.newInstance(), MOVIES_FRAGMENT_TAG);
        transaction.commit();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void toggleLoadingMode(boolean loading) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (loading) {
            transaction.add(R.id.main_fragment_container,
                        LoadingFragment.newInstance(),
                        LOADING_FRAGMENT_TAG)
                        .commit();
        } else {
            LoadingFragment fragment = (LoadingFragment) manager.findFragmentByTag(LOADING_FRAGMENT_TAG);
            if (fragment != null) {
                transaction.remove(fragment).commit();
            }
        }
    }

    @Override
    public void showError() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragment_container,
                ErrorFragment.newInstance(),
                ERROR_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void setActivityTitle(int resId) {
        this.setTitle(resId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        postMenuOptionToFragment(item.getItemId());
        return true;
    }

    private MovieListFragment getMovieListFragment() {
        FragmentManager manager = getFragmentManager();
        return (MovieListFragment) manager.findFragmentByTag(MOVIES_FRAGMENT_TAG);
    }

    private void postMenuOptionToFragment(int optionId) {
        MovieListFragment movieListFragment = getMovieListFragment();

        if (movieListFragment != null) {
            movieListFragment.toggleList(optionId);
        }
    }

    @Override
    public void retryAction() {
        FragmentManager manager = getFragmentManager();
        ErrorFragment errorFragment = (ErrorFragment) manager.findFragmentByTag(ERROR_FRAGMENT_TAG);
        if (errorFragment != null) {
            manager.beginTransaction()
                    .remove(errorFragment)
                    .commit();
        }

        MovieListFragment movieListFragment = getMovieListFragment();

        if (movieListFragment != null) {
            movieListFragment.loadSavedOption();
        }
    }
}