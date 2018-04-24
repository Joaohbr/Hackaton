package br.com.zup.hackatontimesheet.refund_report.ui;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundReportPresenter implements RefundReportContract.Presenter {

    RefundReportContract.View mView;

    public RefundReportPresenter(RefundReportContract.View view) {
        mView = checkNotNull(view);
    }

    @Override
    public void fetchData() {
        mView.setupCurrencySpinner(new String[] {
                "Moeda",
                "Real",
                "Dolar",
                "Euro"
        });
    }
}
