package br.com.zup.hackatontimesheet.refund_approvals.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.expenses.ApprovableRefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusRequest;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusResponse;
import br.com.zup.hackatontimesheet.business_models.expenses.repository.ExpensesRepository;
import br.com.zup.hackatontimesheet.commons.scopes.FragmentScope;
import br.com.zup.hackatontimesheet.refund_approvals.model.RefundApprovalEntry;
import br.com.zup.hackatontimesheet.business_models.user.Employee;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 16/04/2018.
 */

@FragmentScope
public class RefundApprovalsPresenter implements RefundApprovalsContract.Presenter {

    private RefundApprovalsContract.View mView;
    private Employee mEmployee;
    private ExpensesRepository mRepository;
    private List<ApprovableRefundReport> mList = new ArrayList<>();

    @Inject
    public RefundApprovalsPresenter(RefundApprovalsContract.View view, Employee employee, ExpensesRepository repository) {
        this.mView = checkNotNull(view);
        this.mEmployee = checkNotNull(employee);
        this.mRepository = checkNotNull(repository);
    }

    @Override
    public void onAddTimesheet() {
        mView.showProjectDialog(mEmployee.getProjectNameList());
    }

    @Override
    public void onProjectSelected(int index) {
        mView.openRefundReport();
    }

    @Override
    public void fetchApprovals() {
        mView.enableLoading(true);
        mRepository.getApprovableReports(mEmployee.getId(), new ExpensesRepository.ApprovableReportsCallback() {
            @Override
            public void onSuccess(List<ApprovableRefundReport> response) {
                mView.enableLoading(false);
                mList = response;
                mView.showApprovals(convertApprovalList(response));
            }

            @Override
            public void onError() {
                mView.enableLoading(false);
                mView.onError();
            }
        });


    }

    @Override
    public void onItemApproved(final int position) {
        int reportId = Integer.parseInt(mList.get(0).getReportId());
        mView.enableTopProgressBarLoading(true);
        mRepository.putRefundReportStatus(new RefundReportStatusRequest(reportId, "approve"),
                new ExpensesRepository.RefundReportStatusCallback() {
            @Override
            public void onSuccess(List<RefundReportStatusResponse> response) {
                mView.enableTopProgressBarLoading(false);
                mList.remove(position);
                mView.notifyApprovedItem();
            }

            @Override
            public void onError() {
                mView.enableTopProgressBarLoading(false);
                mView.notifyFailRemoveItem();
            }
        });
    }

    @Override
    public void onItemReproved(final int position) {
        int reportId = Integer.parseInt(mList.get(0).getReportId());
        mView.enableTopProgressBarLoading(true);
        mRepository.putRefundReportStatus(new RefundReportStatusRequest(reportId, "approve"),
                new ExpensesRepository.RefundReportStatusCallback() {
                    @Override
                    public void onSuccess(List<RefundReportStatusResponse> response) {
                        mView.enableTopProgressBarLoading(false);
                        mList.remove(position);
                        mView.notifyReprovedItem();
                    }

                    @Override
                    public void onError() {
                        mView.enableTopProgressBarLoading(false);
                        mView.notifyFailRemoveItem();
                    }
                });
    }

    private List<RefundApprovalEntry> convertApprovalList(List<ApprovableRefundReport> approvableReports) {
        List<RefundApprovalEntry> convertedList = new ArrayList<>();

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        for(ApprovableRefundReport report: approvableReports) {

            String date = "";
            try {
                date = outputFormat.format(inputFormat.parse(report.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Double amount = Double.parseDouble(report.getAmount());


            convertedList.add(new RefundApprovalEntry(report.getEmployeeName(), report.getAmount(), date,true));
        }

        return convertedList;
    }
}
