package br.com.zup.hackatontimesheet.business_models.expenses.repository;

import java.util.List;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.business_models.expenses.ApprovableRefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportResponse;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusRequest;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusResponse;
import br.com.zup.hackatontimesheet.commons.scopes.FragmentScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 07/05/2018.
 */

public class ExpensesRepositoryImpl implements ExpensesRepository {

    private ExpensesApi mService;

    @Inject
    public ExpensesRepositoryImpl(ExpensesApi expensesService) {
        this.mService = checkNotNull(expensesService);
    }

    @Override
    public void getApprovableReports(String userId, final ApprovableReportsCallback callback) {
        mService.getApprovableReports(userId).enqueue(new Callback<List<ApprovableRefundReport>>() {
            @Override
            public void onResponse(Call<List<ApprovableRefundReport>> call, Response<List<ApprovableRefundReport>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<List<ApprovableRefundReport>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void putRefundReportStatus(RefundReportStatusRequest request, final RefundReportStatusCallback callback) {
        mService.putRefundReportStatus(request).enqueue(new Callback<List<RefundReportStatusResponse>>() {
            @Override
            public void onResponse(Call<List<RefundReportStatusResponse>> call, Response<List<RefundReportStatusResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<List<RefundReportStatusResponse>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void postRefundReport(RefundReport request, final RefundReportCallback callback) {
        mService.postRefundReport(request).enqueue(new Callback<RefundReportResponse>() {
            @Override
            public void onResponse(Call<RefundReportResponse> call, Response<RefundReportResponse> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<RefundReportResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
