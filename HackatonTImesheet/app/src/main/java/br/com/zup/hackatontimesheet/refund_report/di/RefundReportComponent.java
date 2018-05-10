package br.com.zup.hackatontimesheet.refund_report.di;

import android.content.Intent;

import br.com.zup.hackatontimesheet.business_models.expenses.di.ExpensesRepositoryModule;
import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.refund_report.ui.RefundListFragment;
import br.com.zup.hackatontimesheet.refund_report.ui.RefundReportActivity;
import br.com.zup.hackatontimesheet.refund_report.ui.RefundReportContract;
import br.com.zup.hackatontimesheet.refund_report.ui.RefundReportPresenter;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by joaoh on 07/05/2018.
 */
@ActivityScope
@Subcomponent( modules = {RefundReportComponent.RefundReportModule.class})
public interface RefundReportComponent {

    void inject(RefundReportActivity activity);
    void inject(RefundListFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        RefundReportComponent build();
        @BindsInstance Builder view(RefundReportContract.View view);
        @BindsInstance Builder childView(RefundReportContract.ChildView childView);
        @BindsInstance Builder projectIndex(Integer projectIndex);
    }

    @Module(includes = {ExpensesRepositoryModule.class})
    class RefundReportModule {

        @Provides
        RefundReportContract.Presenter providesPresenter(RefundReportPresenter presenter) {
            return presenter;
        }
    }
}
