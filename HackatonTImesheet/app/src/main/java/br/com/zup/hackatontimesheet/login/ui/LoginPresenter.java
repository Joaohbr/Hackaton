package br.com.zup.hackatontimesheet.login.ui;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.business_models.user.Employee;
import br.com.zup.hackatontimesheet.business_models.user.UserManager;
import br.com.zup.hackatontimesheet.business_models.user.repository.EmployeeRepository;

import static com.google.android.gms.common.internal.zzbq.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    private final String defaultEmail = "rafael.ziggiatti@zup.com.br";
    private UserManager mUserManager;
    private LoginContract.View mView;
    private EmployeeRepository mRepository;

    @Inject
    public LoginPresenter(UserManager userManager, LoginContract.View view, EmployeeRepository employeeRepository) {
        this.mUserManager = checkNotNull(userManager);
        this.mView = checkNotNull(view);
        this.mRepository = checkNotNull(employeeRepository);
    }

    @Override
    public void signIn(GoogleSignInAccount account) {
        mRepository.getEmployee(account.getEmail(), new EmployeeRepository.getEmployeeCallback() {
            @Override
            public void onSuccess(Employee response) {
                loadApplicationData(response);
            }

            @Override
            public void onError() {
                mView.enableLoading(false);
                mView.onError();
            }
        });
    }

    @Override
    public void signOut() {
        mUserManager.logout();
    }

    private void loadApplicationData(final Employee employee) {
        mRepository.getApplicationData(new EmployeeRepository.getApplicationDataCallback() {
            @Override
            public void onSuccess(GeneralData appData) {
                mUserManager.createUserSession(employee, appData);
                mView.enableLoading(false);
                mView.openHomeActivity();
            }

            @Override
            public void onError() {
                mView.enableLoading(false);
                mView.onError();
            }
        });
    }

}
