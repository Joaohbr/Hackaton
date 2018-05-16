package br.com.zup.hackatontimesheet.refund_report.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.expenses.ExpenseImage;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportResponse;
import br.com.zup.hackatontimesheet.business_models.expenses.ReportExpense;
import br.com.zup.hackatontimesheet.business_models.expenses.repository.ExpensesRepository;
import br.com.zup.hackatontimesheet.business_models.user.Employee;
import br.com.zup.hackatontimesheet.business_models.user.GeneralData;
import br.com.zup.hackatontimesheet.commons.scopes.ActivityScope;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */
@ActivityScope
public class RefundReportPresenter implements RefundReportContract.Presenter {

    private final int NO_SELECTION = -1;
    private RefundReportContract.View mView;
    private RefundReportContract.ChildView mChildView;
    private ExpensesRepository mRepository;
    private double totalValue, advanceValue, entriesTotal;
    private int selectedCurrency;
    private Employee mEmployee;
    private GeneralData mAppData;
    private int selectedProject;
    private int editItemIndex = NO_SELECTION;
    private boolean isLoading;

    private final String titleCredit = "Total a receber:", titleDebit = "Total a ser devolvido:";

    @Inject
    public RefundReportPresenter(RefundReportContract.View view, RefundReportContract.ChildView childView, ExpensesRepository repository, Employee employee, GeneralData appData, Integer selectedProject) {
        this.mView = checkNotNull(view);
        this.mChildView = checkNotNull(childView);
        this.mRepository = checkNotNull(repository);
        this.mEmployee = checkNotNull(employee);
        this.mAppData = checkNotNull(appData);
        this.selectedProject = checkNotNull(selectedProject);
        this.totalValue = 0.0f;
        this.advanceValue = 0.0f;
        this.entriesTotal = 0.0f;
        this.selectedCurrency = 0;
        this.isLoading = false;
    }

    @Override
    public void fetchData() {
        mView.setupCurrencySpinner(mAppData.getCurrenciesNameList());

        List<RefundEntry> list = new ArrayList<>();
        mChildView.showRefundEntries(list);
        mView.setupAdvance("0");
        mView.setupProjectName("Projeto: "+mEmployee.getProjects().get(selectedProject).getName());
    }

    @Override
    public void onAddRefundEntry() {
        if(isLoading)
            return;

        mChildView.openRefundEntry(null);
    }

    @Override
    public void onNewRefundEntry(RefundEntry entry) {
        if(editItemIndex != NO_SELECTION) {
            mChildView.updateRefundEntries(entry,editItemIndex);
            editItemIndex = NO_SELECTION;
        } else {
            mChildView.addRefundEntry(entry);
        }
    }

    @Override
    public void onRefundEntryClick(RefundEntry entry, int position) {
        if(isLoading)
            return;

        editItemIndex = position;
        mChildView.openRefundEntry(entry);
    }

    @Override
    public void onSendRefundReport() {
        if(isLoading)
            return;

        List<RefundEntry> entries = mChildView.getEntries();
        if(entries == null || entries.isEmpty()) {
            mView.showEmptyError();
            return;
        }
        RefundReport report = null;
        try {
            report = createReport(convertToReportExpenses(entries));
        } catch (Exception e) {
            e.printStackTrace();
            mView.onError();
        }

        if(report != null) {
            isLoading = true;
            mChildView.enableLoading(true);
            mRepository.postRefundReport(report, new ExpensesRepository.RefundReportCallback() {
                @Override
                public void onSuccess(RefundReportResponse response) {
                    isLoading = false;
                    mChildView.enableLoading(false);
                    mChildView.cleanAdapter();
                    mView.showSuccessDialog();
                }

                @Override
                public void onError() {
                    isLoading = false;
                    mChildView.enableLoading(false);
                }
            });
        } else {
            mView.onError();
        }
    }

    @Override
    public void onAdvanceValueChanged(String advance) {
        totalValue += advanceValue;
        advanceValue = advance.isEmpty() ? 0.0f : Double.parseDouble(advance);
        totalValue -= advanceValue;

        mView.updateTotalValue(getTotalFormatted(), totalValue < 0 ? titleDebit : titleCredit, totalValue < 0);
    }

    @Override
    public void onCurrencySelected(int index) {
        selectedCurrency = index;
        mView.updateTotalValue(getTotalFormatted(), totalValue < 0 ? titleDebit : titleCredit, totalValue < 0);
    }

    @Override
    public void onRefundEntryChanged(double total) {
        totalValue -= entriesTotal;
        entriesTotal = total;
        totalValue += entriesTotal;
        mView.updateTotalValue(getTotalFormatted(), totalValue < 0 ? titleDebit : titleCredit, totalValue < 0 );
    }

    private String getTotalFormatted() {
        String symbol = selectedCurrency == 0 ? "R$" : mAppData.getCurrencies().get(selectedCurrency-1).getSymbol();
        return  symbol + String.format("%.2f", Math.abs(totalValue));
    }

    private RefundReport createReport(List<ReportExpense> expenses) {
        return new RefundReport(
                new Double(advanceValue),
                mEmployee.getExpenseApprover(),
                new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()),
                Integer.parseInt(mEmployee.getId()),
                expenses.size(),
                expenses
        );
    }

    private List<ReportExpense> convertToReportExpenses(List<RefundEntry> entries) {
        List<ReportExpense> result = new ArrayList<>();
        int i = 0;
        for(RefundEntry entry : entries) {
            result.add(new ReportExpense(
                    Double.parseDouble(entry.getValue()),
                    Integer.parseInt(mEmployee.getProjects().get(selectedProject).getBusinessUnitId()),
                    Integer.parseInt(mAppData.getExpenseCategories().get(entry.getCategoryPosition()).getId()),
                    Integer.parseInt(mAppData.getCurrencies().get(selectedCurrency-1).getId()),
                    Integer.parseInt(mEmployee.getProjects().get(selectedProject).getId()),
                    entry.getDate(),
                    Integer.parseInt(mEmployee.getLocationId()),
                    entry.getDescription(),
                    mEmployee.getClassificationId(),
                    new ExpenseImage("receipt"+i++, entry.getImageBase64()))
            );
        }

        return result;
    }

}
