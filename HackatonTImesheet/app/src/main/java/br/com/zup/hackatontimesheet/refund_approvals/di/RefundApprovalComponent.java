package br.com.zup.hackatontimesheet.refund_approvals.di;

import br.com.zup.hackatontimesheet.business_models.expenses.di.ExpensesRepositoryModule;
import br.com.zup.hackatontimesheet.commons.scopes.FragmentScope;
import br.com.zup.hackatontimesheet.refund_approvals.ui.RefundApprovalsContract;
import br.com.zup.hackatontimesheet.refund_approvals.ui.RefundApprovalsListFragment;
import br.com.zup.hackatontimesheet.refund_approvals.ui.RefundApprovalsPresenter;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by joaoh on 07/05/2018.
 */

@FragmentScope
@Subcomponent( modules = {RefundApprovalComponent.RefundApprovalModule.class})
public interface RefundApprovalComponent {

    void inject(RefundApprovalsListFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        RefundApprovalComponent build();
        @BindsInstance Builder view(RefundApprovalsContract.View view);
    }

    @Module(includes = {ExpensesRepositoryModule.class})
    class RefundApprovalModule {

        @Provides
        RefundApprovalsContract.Presenter providesPresenter(RefundApprovalsPresenter presenter) {
            return presenter;
        }
    }
}
