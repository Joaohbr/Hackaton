package br.com.zup.hackatontimesheet.application.di;

import android.app.Application;

import javax.inject.Singleton;

import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.login.di.LoginComponent;
import br.com.zup.hackatontimesheet.business_models.user.UserManager;
import br.com.zup.hackatontimesheet.business_models.user.di.UserComponent;
import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by joaoh on 09/04/2018.
 */

@Singleton
@Component(modules = {AppModule.class,
        NetworkModule.class})
public interface AppComponent {
    void inject(TimesheetApplication application);

    Retrofit getRetrofit();

    UserManager getUserManager();

    LoginComponent.Builder loginComponentbuilder();
    UserComponent.Builder userComponentBuilder();

    @Component.Builder
    interface Builder {
        AppComponent build();
        @BindsInstance Builder application(Application application);
        @BindsInstance Builder baseUrl(String baseUrl);
    }
}
