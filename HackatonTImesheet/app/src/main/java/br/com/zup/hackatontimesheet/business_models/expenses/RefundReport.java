package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoh on 07/05/2018.
 */

public class RefundReport {

    @SerializedName("advance")
    @Expose
    Double advance;
    @SerializedName("approverId")
    @Expose
    String approverId;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("employeeId")
    @Expose
    Integer employeeId;
    @SerializedName("numberOfExpenses")
    @Expose
    Integer numberOfExpenses;
    @SerializedName("expenses")
    @Expose
    List<ReportExpense> expenses;

    public RefundReport() {

    }

    public RefundReport(Double advance, String approverId, String date, Integer employeeId, Integer numberOfExpenses, List<ReportExpense> expenses) {
        this.advance = advance;
        this.approverId = approverId;
        this.date = date;
        this.employeeId = employeeId;
        this.numberOfExpenses = numberOfExpenses;
        this.expenses = expenses;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getNumberOfExpenses() {
        return numberOfExpenses;
    }

    public void setNumberOfExpenses(Integer numberOfExpenses) {
        this.numberOfExpenses = numberOfExpenses;
    }

    public List<ReportExpense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ReportExpense> expenses) {
        this.expenses = expenses;
    }
}
