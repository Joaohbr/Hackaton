package br.com.zup.hackatontimesheet.business_models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoh on 07/05/2018.
 */

public class GeneralData {

    @SerializedName("currencies")
    @Expose
    List<Currency> currencies;
    @SerializedName("hourCategories")
    @Expose
    List<Category> hourCategories;
    @SerializedName("expenseCategories")
    @Expose
    List<Category> expenseCategories;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Category> getHourCategories() {
        return hourCategories;
    }

    public void setHourCategories(List<Category> hourCategories) {
        this.hourCategories = hourCategories;
    }

    public List<Category> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(List<Category> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }
}
