package br.com.zup.hackatontimesheet.business_models.expenses.repository;

import java.util.List;

import br.com.zup.hackatontimesheet.business_models.expenses.ApprovableRefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusRequest;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusResponse;
import br.com.zup.hackatontimesheet.commons.interfaces.Callback;

/**
 * Created by joaoh on 07/05/2018.
 */

public interface ExpensesRepository {
    void getApprovableReports(String userId, ApprovableReportsCallback callback);
    interface ApprovableReportsCallback extends Callback<List<ApprovableRefundReport>> {}

    void putRefundReportStatus(RefundReportStatusRequest request, RefundReportStatusCallback callback);
    interface RefundReportStatusCallback extends Callback<List<RefundReportStatusResponse>>{}
}
