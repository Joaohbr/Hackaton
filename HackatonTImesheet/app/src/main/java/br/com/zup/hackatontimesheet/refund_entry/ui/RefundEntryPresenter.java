package br.com.zup.hackatontimesheet.refund_entry.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundEntryPresenter implements RefundEntryContract.Presenter {

    private RefundEntryContract.View mView;
    private boolean isEdition;

    public RefundEntryPresenter(RefundEntryContract.View view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void fetchData(RefundEntry selectedEntry) {
        mView.setupCategorySpinner(new String[] {"Categoria...",
                "Abono pecuniario",
                "Adiantamento viagens",
                "Adicional contrato prestação servico pj",
                "Agua",
                "Alimentação"
        });

        if(selectedEntry != null) {
            mView.showSelectedEntry(selectedEntry);
            isEdition = true;
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
        mView.finish(entry, isEdition);
    }
}
