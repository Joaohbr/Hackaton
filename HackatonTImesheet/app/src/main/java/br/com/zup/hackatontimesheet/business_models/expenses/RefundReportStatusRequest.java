package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by joaoh on 07/05/2018.
 */

public class RefundReportStatusRequest {
    @SerializedName("expensesReportId")
    @Expose
    Integer expensesReportId;
    @SerializedName("action")
    @Expose
    String action;

    public RefundReportStatusRequest(Integer reportId, String action) {
        this.expensesReportId = checkNotNull(reportId);
        this.action = checkNotNull(action);
    }
}
