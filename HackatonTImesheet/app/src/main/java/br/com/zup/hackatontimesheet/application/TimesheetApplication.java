package br.com.zup.hackatontimesheet.application;

import android.app.Application;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.di.AppComponent;
import br.com.zup.hackatontimesheet.application.di.DaggerAppComponent;

/**
 * Created by joaoh on 09/04/2018.
 */

public class TimesheetApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .baseUrl(getString(R.string.api_base_url))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
