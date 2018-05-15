package br.com.zup.hackatontimesheet.business_models.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExpenseImage {

    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("content")
    @Expose
    String imageBase64;

    public ExpenseImage(String name, String imageBase64) {
        this.name = checkNotNull(name);
        this.imageBase64 = checkNotNull(imageBase64);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
