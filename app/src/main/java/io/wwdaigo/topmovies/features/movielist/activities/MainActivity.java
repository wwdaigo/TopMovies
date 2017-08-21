package io.wwdaigo.topmovies.features.movielist.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.ActivityMainBinding;
import io.wwdaigo.topmovies.features.movielist.adapters.MovieListAdapter;
import io.wwdaigo.topmovies.features.movielist.adapters.OnSelectMovieData;
import io.wwdaigo.topmovies.features.movielist.fragments.ErrorFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.LoadingFragment;
import io.wwdaigo.topmovies.features.movielist.fragments.OnErrorFragmentInteraction;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;
import io.wwdaigo.topmovies.router.MainRouterType;

import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.ERROR_FRAGMENT_TAG;
import static io.wwdaigo.topmovies.commons.Constants.FragmentTags.LOADING_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements
        HasFragmentInjector,
        OnSelectMovieData,
        OnErrorFragmentInteraction {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    MovieListViewModelType viewModel;

    @Inject
    MovieListAdapter movieListAdapter;

    @Inject
    MainRouterType mainRouter;

    private CompositeDisposable disposable;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        disposable = new CompositeDisposable();

        bindMovieListRecyclerView();
        bindOutputs();
        viewModel.getInputs().loadSavedOption(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void bindOutputs() {
        Disposable loadingDisposable = viewModel.getOutputs().isLoading().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean isLoading) throws Exception {
                toggleLoadingMode(isLoading);
            }
        });

        Disposable hasErrorDisposable = viewModel.getOutputs().hasError().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean hasError) throws Exception {
                if (hasError) showError();
            }
        });

        Disposable titleStringResourceDisposable = viewModel.getOutputs().getTitleStringResource().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer res) throws Exception {
                setActivityTitle(res);
            }
        });

        disposable.addAll(loadingDisposable, hasErrorDisposable, titleStringResourceDisposable);
    }

    private void bindMovieListRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns());
        movieListAdapter.setObservableList(viewModel.getOutputs().listMovieData());
        movieListAdapter.setOnSelectMovieData(this);

        binding.movieListRecyclerView.setHasFixedSize(true);
        binding.movieListRecyclerView.setLayoutManager(gridLayoutManager);
        binding.movieListRecyclerView.setAdapter(movieListAdapter);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int minCols = getResources().getInteger(R.integer.poster_min_cols);
        int widthDivider = getResources().getInteger(R.integer.poster_width);
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < minCols) return minCols;
        return nColumns;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

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

    public void showError() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragment_container,
                ErrorFragment.newInstance(),
                ERROR_FRAGMENT_TAG)
                .commit();
    }

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

    private void postMenuOptionToFragment(int optionId) {

        viewModel.getInputs().saveOption(this, optionId);
        switch (optionId) {
            case R.id.menu_main_popular:
                viewModel.getInputs().loadMostPopularMovies();
                break;

            case R.id.menu_main_top_rated:
                viewModel.getInputs().loadTopRatedMovies();
                break;
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

        viewModel.getInputs().loadSavedOption(this);
    }

    @Override
    public void selectMovieData(MovieData movieData) {
        mainRouter.openMovie(this, movieData);
    }
}