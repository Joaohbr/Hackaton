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
    private int categoryPosition;
    private String date;

    public RefundEntry() {
    }

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

    public int getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(int categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected RefundEntry(Parcel in) {
        description = in.readString();
        value = in.readString();
        categoryPosition = in.readInt();
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
        dest.writeInt(categoryPosition);
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