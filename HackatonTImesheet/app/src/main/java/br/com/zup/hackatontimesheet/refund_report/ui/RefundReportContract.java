package br.com.zup.hackatontimesheet.refund_report.ui;

import java.util.List;

import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface RefundReportContract {
    interface View {
        void setupCurrencySpinner(String [] currencies);
    }

    interface ChildView {
        void bindPresenter(RefundReportContract.Presenter presenter);

        void showRefundEntries(List<RefundEntry> list);

        void openRefundEntry(RefundEntry entry);
    }

    interface Presenter {
        void fetchData();

        void onAddRefundEntry();

        void onRefundEntryClick(RefundEntry entry, int position);
    }
}
