package br.com.zup.hackatontimesheet.application.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by joaoh on 09/04/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Retrofit getRetrofit();

    @Component.Builder
    interface Builder {
        AppComponent build();
        @BindsInstance Builder application(Application application);
        @BindsInstance Builder baseUrl(String baseUrl);
    }
}
