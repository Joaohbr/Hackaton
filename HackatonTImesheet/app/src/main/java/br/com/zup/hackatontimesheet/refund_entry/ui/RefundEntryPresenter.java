package br.com.zup.hackatontimesheet.refund_entry.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */
@ActivityScope
public class RefundEntryPresenter implements RefundEntryContract.Presenter {

    private RefundEntryContract.View mView;
    private GeneralData mAppData;

    @Inject
    public RefundEntryPresenter(RefundEntryContract.View view, GeneralData appData) {
        this.mView = checkNotNull(view);
        this.mAppData = checkNotNull(appData);
    }

    @Override
    public void fetchData(RefundEntry selectedEntry) {
        mView.setupCategorySpinner(mAppData.getExpenseCategoriesList());

        if(selectedEntry != null) {
            mView.showSelectedEntry(selectedEntry);
        }

    }

    @Override
    public void onSelectedDate(Calendar calendar) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        mView.updateSelectedDate(sdf.format(calendar.getTime()));
    }

    @Override
    public void onSaveReport(RefundEntry entry) {
        mView.finish(entry);
    }
}
