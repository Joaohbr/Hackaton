package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 07/05/2018.
 */

public class ReportExpense {

    @SerializedName("amount")
    @Expose
    Double amount;
    @SerializedName("businessUnitId")
    @Expose
    Integer businessUnitId;
    @SerializedName("categoryId")
    @Expose
    Integer categoryId;
    @SerializedName("currencyId")
    @Expose
    Integer currencyId;
    @SerializedName("customerProjectId")
    @Expose
    Integer customerProjectId;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("locationId")
    @Expose
    Integer locationId;
    @SerializedName("memo")
    @Expose
    String memo;
    @SerializedName("costCenterId")
    @Expose
    String costCenterId;
    @SerializedName("file")
    ExpenseImage image;


    public ReportExpense() {

    }

    public ReportExpense(Double amount, Integer businessUnitId, Integer categoryId, Integer currencyId, Integer customerProjectId, String date, Integer locationId, String memo, String costCenterId, ExpenseImage image) {
        this.amount = checkNotNull(amount);
        this.businessUnitId = checkNotNull(businessUnitId);
        this.categoryId = checkNotNull(categoryId);
        this.currencyId = checkNotNull(currencyId);
        this.customerProjectId = checkNotNull(customerProjectId);
        this.date = checkNotNull(date);
        this.locationId = checkNotNull(locationId);
        this.memo = checkNotNull(memo);
        this.costCenterId = checkNotNull(costCenterId);
        this.image = checkNotNull(image);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Integer businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getCustomerProjectId() {
        return customerProjectId;
    }

    public void setCustomerProjectId(Integer customerProjectId) {
        this.customerProjectId = customerProjectId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ExpenseImage getImage() {
        return image;
    }

    public void setImage(ExpenseImage image) {
        this.image = image;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
