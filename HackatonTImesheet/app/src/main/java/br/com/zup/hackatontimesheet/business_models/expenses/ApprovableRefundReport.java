package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoh on 07/05/2018.
 */

public class ApprovableRefundReport {

    @SerializedName("reportId")
    @Expose
    String reportId;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("amount")
    @Expose
    String amount;
    @SerializedName("advance")
    @Expose
    Double advance;
    @SerializedName("employeeName")
    @Expose
    String employeeName;
    @SerializedName("projectName")
    @Expose
    String projectName;
    @SerializedName("expenses")
    @Expose
    List<Expense> expenses;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
