package br.com.zup.hackatontimesheet.home.di;

import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.home.ui.HomeContract;
import br.com.zup.hackatontimesheet.refund_approvals.di.RefundApprovalComponent;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by joaoh on 09/04/2018.
 */

@ActivityScope
@Subcomponent(modules = HomeComponent.HomeModule.class)
public interface HomeComponent {

    RefundApprovalComponent.Builder getRefudApprovalBuilder();

    @Subcomponent.Builder
    interface Builder {
        HomeComponent build();
        @BindsInstance Builder view(HomeContract.View view);
    }

    @Module( subcomponents = {RefundApprovalComponent.class} )
    class HomeModule {

    }
}
