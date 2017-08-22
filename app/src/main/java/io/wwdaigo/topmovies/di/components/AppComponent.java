package io.wwdaigo.topmovies.di.components;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import io.wwdaigo.topmovies.App;
import io.wwdaigo.topmovies.di.modules.ActivityModule;
import io.wwdaigo.topmovies.di.modules.AppModule;
import io.wwdaigo.topmovies.di.modules.MainActivityModule;
import io.wwdaigo.topmovies.di.modules.RemoteModule;
import io.wwdaigo.topmovies.di.modules.SearchActivityModule;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

@Component(modules = {AndroidInjectionModule.class, AppModule.class, MainActivityModule.class, SearchActivityModule.class, RemoteModule.class, ActivityModule.class})
public interface AppComponent {
    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);
        Builder appModule(AppModule appModule);
        AppComponent build();
    }
}
