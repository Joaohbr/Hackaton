package br.com.zup.hackatontimesheet.business_models.user.di;

import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.UserScope;
import br.com.zup.hackatontimesheet.home.di.HomeComponent;
import br.com.zup.hackatontimesheet.business_models.user.Employee;
import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by joaoh on 06/05/2018.
 */

@UserScope
@Subcomponent(modules = UserComponent.UserModule.class)
public interface UserComponent {

    HomeComponent.Builder getHomeComponentBuilder();

    @Subcomponent.Builder
    interface Builder {
        UserComponent build();
        @BindsInstance Builder employee(Employee employee);
        @BindsInstance Builder applicationData(GeneralData applicationpData);
    }

    @Module(subcomponents = {HomeComponent.class})
    class UserModule {

    }
}
