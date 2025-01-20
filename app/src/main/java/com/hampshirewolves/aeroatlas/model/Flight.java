package com.hampshirewolves.aeroatlas.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Flight extends BaseObservable implements Parcelable {

    private String id;
    private String price;
    private String currency;
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;


    public Flight() {
    }

    public Flight(String id, String price, String currency, String origin, String destination, String departureDate, String arrivalDate) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    protected Flight(Parcel in) {
        id = in.readString();
        price = in.readString();
        currency = in.readString();
        origin = in.readString();
        destination = in.readString();
        departureDate = in.readString();
        arrivalDate = in.readString();
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    @Bindable
    public String getId() {
        return id;
    }
    @Bindable
    public String getPrice() {
        return price;
    }
    @Bindable
    public String getCurrency() {
        return currency;
    }
    @Bindable
    public String getOrigin() {
        return origin;
    }
    @Bindable
    public String getDestination() {
        return destination;
    }
    @Bindable
    public String getDepartureDate() {
        return departureDate;
    }
    @Bindable
    public String getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(price);
        dest.writeString(currency);
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(departureDate);
        dest.writeString(arrivalDate);
    }
}
