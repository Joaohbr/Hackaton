package br.com.zup.hackatontimesheet.business_models.user;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.zup.hackatontimesheet.business_models.user.di.UserComponent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 06/05/2018.
 */

@Singleton
public class UserManager {

    private UserComponent userComponent;
    private UserComponent.Builder mUserComponentBuilder;

    @Inject
    public UserManager(UserComponent.Builder userComponentBuilder) {
        this.mUserComponentBuilder = checkNotNull(userComponentBuilder);
    }

    public void createUserSession(Employee employee, GeneralData applicationData) {
        this.userComponent = mUserComponentBuilder
                .employee(employee)
                .applicationData(applicationData)
                .build();
    }

    public void logout() {
        this.userComponent = null;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
