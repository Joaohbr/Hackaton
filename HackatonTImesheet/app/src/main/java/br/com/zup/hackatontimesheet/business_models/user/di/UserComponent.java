package br.com.zup.hackatontimesheet.business_models.user.di;

import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.UserScope;
import br.com.zup.hackatontimesheet.home.di.HomeComponent;
import br.com.zup.hackatontimesheet.business_models.user.Employee;
import br.com.zup.hackatontimesheet.refund_entry.di.RefundEntryComponent;
import br.com.zup.hackatontimesheet.refund_report.di.RefundReportComponent;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by joaoh on 06/05/2018.
 */

@UserScope
@Subcomponent(modules = UserComponent.UserModule.class)
public interface UserComponent {

    HomeComponent.Builder getHomeComponentBuilder();
    RefundReportComponent.Builder getRefundReportComponentBuilder();
    RefundEntryComponent.Builder getRefundEntryComponentBuilder();

    @Subcomponent.Builder
    interface Builder {
        UserComponent build();
        @BindsInstance Builder employee(Employee employee);
        @BindsInstance Builder applicationData(GeneralData applicationpData);
    }

    @Module(subcomponents = {HomeComponent.class, RefundReportComponent.class, RefundEntryComponent.class})
    class UserModule {

    }
}
