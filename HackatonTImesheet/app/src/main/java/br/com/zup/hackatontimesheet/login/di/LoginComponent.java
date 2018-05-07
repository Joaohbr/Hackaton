package br.com.zup.hackatontimesheet.login.di;

import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.login.ui.LoginActivity;
import br.com.zup.hackatontimesheet.login.ui.LoginContract;
import br.com.zup.hackatontimesheet.login.ui.LoginPresenter;
import br.com.zup.hackatontimesheet.business_models.user.di.EmployeeRepositoryModule;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by joaoh on 07/05/2018.
 */

@ActivityScope
@Subcomponent( modules = {LoginComponent.LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);

    @Subcomponent.Builder
    interface Builder {
        LoginComponent build();
        @BindsInstance Builder view(LoginContract.View view);
    }

    @Module (includes = EmployeeRepositoryModule.class)
    class LoginModule {

        @Provides
        public LoginContract.Presenter provideLoginPresenter(LoginPresenter presenter) {
            return presenter;
        }
    }
}
