package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoh on 07/05/2018.
 */

public class RefundReportResponse {

    @SerializedName("reportId")
    @Expose
    Integer reportId;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
}
