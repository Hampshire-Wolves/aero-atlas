package com.hampshirewolves.aeroatlas.model;

import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Flights extends BaseObservable implements Parcelable {

    private String id;
    private String price;
    private String currency;
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;


    public Flights() {
    }

    public Flights(String id, String price, String currency, String origin, String destination, String departureDate, String arrivalDate) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
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
}
