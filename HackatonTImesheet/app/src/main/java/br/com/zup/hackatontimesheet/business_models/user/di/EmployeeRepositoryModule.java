package br.com.zup.hackatontimesheet.business_models.user.di;

import br.com.zup.hackatontimesheet.business_models.user.repository.EmployeeApi;
import br.com.zup.hackatontimesheet.business_models.user.repository.EmployeeRepository;
import br.com.zup.hackatontimesheet.business_models.user.repository.EmployeeRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

/**
 * Created by joaoh on 06/05/2018.
 */

@Module
public class EmployeeRepositoryModule {

    @Reusable
    @Provides
    EmployeeApi providesClientApi(Retrofit retrofit) {
        return retrofit.create(EmployeeApi.class);
    }

    @Reusable
    @Provides
    EmployeeRepository providesRepository(EmployeeRepositoryImpl repository) {
        return repository;
    }
}
