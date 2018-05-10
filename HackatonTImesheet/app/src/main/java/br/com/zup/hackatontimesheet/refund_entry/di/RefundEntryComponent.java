package br.com.zup.hackatontimesheet.refund_entry.di;

import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.refund_entry.ui.RefundEntryActivity;
import br.com.zup.hackatontimesheet.refund_entry.ui.RefundEntryContract;
import br.com.zup.hackatontimesheet.refund_entry.ui.RefundEntryPresenter;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by joaoh on 07/05/2018.
 */

@ActivityScope
@Subcomponent(modules = {RefundEntryComponent.RefundEntryModule.class} )
public interface RefundEntryComponent {

    void inject(RefundEntryActivity activity);

    @Subcomponent.Builder
    interface Builder {
        RefundEntryComponent build();
        @BindsInstance Builder view(RefundEntryContract.View view);
    }

    @Module
    class RefundEntryModule {

        @Provides
        RefundEntryContract.Presenter providesRefundEntryPresenter(RefundEntryPresenter presenter) {
            return presenter;
        }
    }
}
