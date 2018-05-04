package br.com.zup.hackatontimesheet.refund_entry.ui;

import java.util.Calendar;

import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface RefundEntryContract {
    interface View {
        void setupCategorySpinner(String [] categories);

        void updateSelectedDate(String date);

        void finish(RefundEntry entry, boolean isEdition);

        void showSelectedEntry(RefundEntry entry);
    }

    interface Presenter {
        void fetchData(RefundEntry selectedEntry);

        void onSelectedDate(Calendar calendar);

        void onSaveReport(RefundEntry entry);
    }
}
