package com.claraberriel.mvpparking.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Reservation implements Parcelable {
    private long startDateTime;
    private long endDateTime;
    private int parkingNumber;
    private String securityCode;

    public Reservation(long startDateTime, long endDateTime, int parkingNumber, String securityCode) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.parkingNumber = parkingNumber;

        this.securityCode = securityCode;
    }

    /**
     * ---- Getters ----
     */

    public int getParkingNumber() {
        return parkingNumber;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * ---- Setters ----
     */

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.parkingNumber);
        dest.writeLong(this.startDateTime);
        dest.writeLong(this.endDateTime);
        dest.writeString(this.securityCode);
    }

    public void readFromParcel(Parcel source) {
        this.parkingNumber = source.readInt();
        this.startDateTime = source.readLong();
        this.endDateTime = source.readLong();
        this.securityCode = source.readString();
    }

    public Reservation() {
    }

    public Reservation(Parcel in) {
        this.parkingNumber = in.readInt();
        this.startDateTime = in.readLong();
        this.endDateTime = in.readLong();
        this.securityCode = in.readString();
    }

    public static final Parcelable.Creator<Reservation> CREATOR = new Parcelable.Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel source) {
            return new Reservation(source);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };
}
