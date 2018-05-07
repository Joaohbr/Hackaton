package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoh on 07/05/2018.
 */

public class Expense {

    @SerializedName("amount")
    @Expose
    Double amount;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("categoryName")
    @Expose
    String categoryName;
    @SerializedName("memo")
    @Expose
    String memo;
    @SerializedName("imageId")
    @Expose
    String imageId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
