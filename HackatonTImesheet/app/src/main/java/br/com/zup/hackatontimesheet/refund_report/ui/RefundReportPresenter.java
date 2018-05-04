package br.com.zup.hackatontimesheet.refund_report.ui;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundReportPresenter implements RefundReportContract.Presenter {

    RefundReportContract.View mView;
    RefundReportContract.ChildView mChildView;

    public RefundReportPresenter(RefundReportContract.View view, RefundReportContract.ChildView childView) {
        mView = checkNotNull(view);
        mChildView = checkNotNull(childView);
    }

    @Override
    public void fetchData() {
        mView.setupCurrencySpinner(new String[] {
                "Moeda",
                "Real",
                "Dolar",
                "Euro",
        });

        List<RefundEntry> list = new ArrayList<>();
        mChildView.showRefundEntries(list);
    }

    @Override
    public void onAddRefundEntry() {
        mChildView.openRefundEntry(null);
    }

    @Override
    public void onRefundEntryClick(RefundEntry entry, int position) {
        mChildView.openRefundEntry(entry);
    }

}
