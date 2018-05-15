package br.com.zup.hackatontimesheet.application;

import android.app.Application;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.di.AppComponent;
import br.com.zup.hackatontimesheet.application.di.DaggerAppComponent;
import br.com.zup.hackatontimesheet.business_models.user.UserManager;
import br.com.zup.hackatontimesheet.business_models.user.di.UserComponent;

/**
 * Created by joaoh on 09/04/2018.
 */

public class TimesheetApplication extends Application {

    private AppComponent mAppComponent;
    @Inject
    UserManager userManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .baseUrl(getString(R.string.api_base_url))
                .build();
        mAppComponent.inject(this);

    }

    public boolean isLogged() {
        return userManager.isLogged();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public UserComponent getUserComponent() {
        return userManager.getUserComponent();
    }
}
