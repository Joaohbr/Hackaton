package br.com.zup.hackatontimesheet.business_models.expenses.repository;

import java.util.List;

import br.com.zup.hackatontimesheet.business_models.expenses.ApprovableRefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReport;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportResponse;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusRequest;
import br.com.zup.hackatontimesheet.business_models.expenses.RefundReportStatusResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by joaoh on 07/05/2018.
 */

public interface ExpensesApi {

    @GET("restlet.nl?script=340&deploy=1")
    Call<List<ApprovableRefundReport>> getApprovableReports(@Query("managerId") String managerId);

    @PUT("restlet.nl?script=339&deploy=1")
    Call<List<RefundReportStatusResponse>> putRefundReportStatus(@Body RefundReportStatusRequest reportStatusRequest);

    @POST("restlet.nl?script=335&deploy=1")
    Call<RefundReportResponse> postRefundReport(@Body RefundReport refundReport);
}
