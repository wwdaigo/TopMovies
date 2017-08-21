package io.wwdaigo.topmovies.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.wwdaigo.topmovies.features.details.activities.DetailsActivity;
import io.wwdaigo.topmovies.features.movielist.activities.MainActivity;
import io.wwdaigo.topmovies.features.search.activities.SearchActivity;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivityInjector();

    @ContributesAndroidInjector()
    abstract DetailsActivity contributeDetailActivityInjector();

    @ContributesAndroidInjector()
    abstract SearchActivity contributeSearchActivityInjector();


}
