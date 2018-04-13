package br.com.zup.hackatontimesheet.application.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joaoh on 09/04/2018.
 */

@Module
public class AppModule {
    String DATA_STORE = "app-shared-prefs";

    @Provides
    @Singleton
    public SharedPreferences providePreferences(Application application) {
        return application.getSharedPreferences(DATA_STORE,
                Context.MODE_PRIVATE);
    }
}
