package br.com.zup.hackatontimesheet.business_models.expenses.di;

import br.com.zup.hackatontimesheet.business_models.expenses.repository.ExpensesApi;
import br.com.zup.hackatontimesheet.business_models.expenses.repository.ExpensesRepository;
import br.com.zup.hackatontimesheet.business_models.expenses.repository.ExpensesRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

/**
 * Created by joaoh on 07/05/2018.
 */

@Module
public class ExpensesRepositoryModule {

    @Reusable
    @Provides
    ExpensesApi provideExpensesApi(Retrofit retrofit) {
        return retrofit.create(ExpensesApi.class);
    }

    @Reusable
    @Provides
    ExpensesRepository provideExpensesRepository(ExpensesRepositoryImpl repository) {
        return repository;
    }
}
