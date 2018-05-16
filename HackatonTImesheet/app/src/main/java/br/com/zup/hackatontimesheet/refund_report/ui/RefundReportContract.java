package br.com.zup.hackatontimesheet.refund_report.ui;

import java.util.List;

import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface RefundReportContract {
    interface View {
        void setupAdvance(String advance);

        void setupCurrencySpinner(String [] currencies);

        void setupProjectName(String projectName);

        void updateTotalValue(String total, String title, boolean isNegative);

        void showEmptyError();

        void showSuccessDialog();

        void onError();
    }

    interface ChildView {
        void bindPresenter(RefundReportContract.Presenter presenter);

        void showRefundEntries(List<RefundEntry> list);

        void openRefundEntry(RefundEntry entry);

        void updateRefundEntries(RefundEntry entry, int position);

        void addRefundEntry(RefundEntry entry);

        void cleanAdapter();

        void enableLoading(boolean enable);

        List<RefundEntry> getEntries();
    }

    interface Presenter {
        void fetchData();

        void onAddRefundEntry();

        void onNewRefundEntry(RefundEntry entry);

        void onRefundEntryClick(RefundEntry entry, int position);

        void onSendRefundReport();

        void onAdvanceValueChanged(String advance);

        void onCurrencySelected(int index);

        void onRefundEntryChanged(double total);
    }
}
