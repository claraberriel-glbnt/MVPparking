package com.claraberriel.mvpparking.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Parking implements Parcelable {
    private ArrayList<Reservation> reservations;
    private int parkingSize;

    public Parking(int parkingSize) {
        this.reservations = new ArrayList<>();
        this.parkingSize = parkingSize;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int parkingSize) {
        this.parkingSize = parkingSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.reservations);
        dest.writeInt(this.parkingSize);
    }

    public void readFromParcel(Parcel source) {
        this.reservations = new ArrayList<Reservation>();
        source.readList(this.reservations, Reservation.class.getClassLoader());
        this.parkingSize = source.readInt();
    }

    protected Parking(Parcel in) {
        this.reservations = new ArrayList<Reservation>();
        in.readList(this.reservations, Reservation.class.getClassLoader());
        this.parkingSize = in.readInt();
    }

    public static final Parcelable.Creator<Parking> CREATOR = new Parcelable.Creator<Parking>() {
        @Override
        public Parking createFromParcel(Parcel source) {
            return new Parking(source);
        }

        @Override
        public Parking[] newArray(int size) {
            return new Parking[size];
        }
    };
}
