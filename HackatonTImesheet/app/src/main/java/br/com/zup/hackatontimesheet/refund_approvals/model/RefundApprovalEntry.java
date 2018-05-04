package br.com.zup.hackatontimesheet.refund_approvals.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 19/04/2018.
 */

public class RefundApprovalEntry {

    private String name;
    private String value;
    private String date;
    private boolean isNegativeValue;


    public RefundApprovalEntry(String name, String value, String date, boolean isNegativeValue) {
        this.name = checkNotNull(name);
        this.value = checkNotNull(value);
        this.date = checkNotNull(date);
        this.isNegativeValue = isNegativeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isNegativeValue() {
        return isNegativeValue;
    }

    public void setNegativeValue(boolean negativeValue) {
        isNegativeValue = negativeValue;
    }
}
