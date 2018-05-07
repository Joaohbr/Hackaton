package br.com.zup.hackatontimesheet.application.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import br.com.zup.hackatontimesheet.login.di.LoginComponent;
import br.com.zup.hackatontimesheet.business_models.user.di.UserComponent;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joaoh on 09/04/2018.
 */

@Module(subcomponents = {UserComponent.class, LoginComponent.class})
public class AppModule {
    String DATA_STORE = "app-shared-prefs";

    @Provides
    @Singleton
    public SharedPreferences providePreferences(Application application) {
        return application.getSharedPreferences(DATA_STORE,
                Context.MODE_PRIVATE);
    }
}
