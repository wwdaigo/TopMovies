package io.wwdaigo.topmovies.dagger.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.wwdaigo.topmovies.features.movielist.activity.MainActivity;
import io.wwdaigo.topmovies.features.movielist.fragments.MovieListFragment;

/**
 * Created by daigomatsuoka on 18/08/17.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();

    @ContributesAndroidInjector
    abstract MovieListFragment movieListFragmentInjector();
    
}
