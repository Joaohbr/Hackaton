package br.com.zup.hackatontimesheet.business_models.user.repository;

import br.com.zup.hackatontimesheet.business_models.user.Employee;
import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joaoh on 06/05/2018.
 */

public interface EmployeeApi {

    @GET("restlet.nl?script=318&deploy=1")
    Call<Employee> getEmployee(@Query("email") String email);

    @GET("restlet.nl?script=320&deploy=1&subsidiary=1")
    Call<GeneralData> getApplicationData();
}
