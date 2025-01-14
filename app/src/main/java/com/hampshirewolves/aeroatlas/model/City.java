package com.hampshirewolves.aeroatlas.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import java.time.LocalDateTime;

public class City extends BaseObservable implements Parcelable {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;   //Does this need to be an IMG?
    private String country;
    private String lat;
    private String lng;
    private String iataCode;
    private String starRating; //Enum
    private String priceRating; //Enum
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public City() {
    }

    public City(Long id, String name, String description, String imageUrl, String country,
                String lat, String lng, String iataCode, String starRating,
                String priceRating, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
        this.iataCode = iataCode;
        this.starRating = starRating;
        this.priceRating = priceRating;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    protected City(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        country = in.readString();
        lat = in.readString();
        lng = in.readString();
        iataCode = in.readString();
        starRating = in.readString();
        priceRating = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(country);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(iataCode);
        dest.writeString(starRating);
        dest.writeString(priceRating);
    }
@Bindable
    public Long getId() {
        return id;
    }
@Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public String getDescription() {
        return description;
    }
    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }
    @Bindable
    public String getCountry() {
        return country;
    }
    @Bindable
    public String getLat() {
        return lat;
    }
    @Bindable
    public String getLng() {
        return lng;
    }
    @Bindable
    public String getIataCode() {
        return iataCode;
    }
    @Bindable
    public String getStarRating() {
        return starRating;
    }
    @Bindable
    public String getPriceRating() {
        return priceRating;
    }
    @Bindable
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @Bindable
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
