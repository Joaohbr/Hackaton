package br.com.zup.hackatontimesheet.business_models.user.repository;


import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.interfaces.Callback;
import br.com.zup.hackatontimesheet.business_models.user.Employee;

/**
 * Created by joaoh on 06/05/2018.
 */

public interface EmployeeRepository {
    void getEmployee(String email, getEmployeeCallback callback);
    interface getEmployeeCallback extends Callback<Employee> {}

    void getApplicationData(getApplicationDataCallback callback);
    interface getApplicationDataCallback extends Callback<GeneralData> {}
}