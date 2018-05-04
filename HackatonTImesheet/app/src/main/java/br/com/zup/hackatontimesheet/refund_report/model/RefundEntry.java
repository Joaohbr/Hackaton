package br.com.zup.hackatontimesheet.refund_report.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 25/04/2018.
 */

public class RefundEntry implements Parcelable {

    private String description;
    private String value;
    private String category;
    private String date;

    public RefundEntry(){}

    public RefundEntry(String description, String value, String date) {
        this.description = checkNotNull(description);
        this.value = checkNotNull(value);
        this.date = checkNotNull(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    //Parcelable implementation
    protected RefundEntry(Parcel in) {
        description = in.readString();
        value = in.readString();
        category = in.readString();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(value);
        dest.writeString(category);
        dest.writeString(date);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RefundEntry> CREATOR = new Parcelable.Creator<RefundEntry>() {
        @Override
        public RefundEntry createFromParcel(Parcel in) {
            return new RefundEntry(in);
        }

        @Override
        public RefundEntry[] newArray(int size) {
            return new RefundEntry[size];
        }
    };

}
