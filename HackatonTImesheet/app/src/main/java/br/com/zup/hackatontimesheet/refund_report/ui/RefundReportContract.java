package br.com.zup.hackatontimesheet.refund_report.ui;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface RefundReportContract {
    interface View {
        void setupCurrencySpinner(String [] currencies);
    }

    interface Presenter {
        void fetchData();
    }
}
