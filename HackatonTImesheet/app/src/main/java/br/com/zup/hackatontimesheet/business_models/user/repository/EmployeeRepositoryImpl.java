package br.com.zup.hackatontimesheet.business_models.user.repository;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.business_models.user.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 06/05/2018.
 */

@ActivityScope
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeApi mEmployeeService;

    @Inject
    public EmployeeRepositoryImpl(EmployeeApi employeeService) {
        this.mEmployeeService = checkNotNull(employeeService);
    }

    @Override
    public void getEmployee(String email, final getEmployeeCallback callback) {
        mEmployeeService.getEmployee(email).enqueue(new retrofit2.Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void getApplicationData(final getApplicationDataCallback callback) {
        mEmployeeService.getApplicationData().enqueue(new Callback<GeneralData>() {
            @Override
            public void onResponse(Call<GeneralData> call, Response<GeneralData> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<GeneralData> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
