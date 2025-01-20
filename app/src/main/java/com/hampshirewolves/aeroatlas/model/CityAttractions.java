package com.hampshirewolves.aeroatlas.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import java.time.LocalDateTime;

public class CityAttractions extends BaseObservable implements Parcelable {

    private Long id;
    private String name;
    private String imageUrl;
    private String createdAt;
    private String modifiedAt;

    public CityAttractions(){};

    public CityAttractions(Long id, String name, String imageUrl, String createdAt, String modifiedAt){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    protected CityAttractions(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        id = in.readLong();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<CityAttractions> CREATOR = new Creator<CityAttractions>() {
        @Override
        public CityAttractions createFromParcel(Parcel in) {
            return new CityAttractions(in);
        }

        @Override
        public CityAttractions[] newArray(int size) {
            return new CityAttractions[size];
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
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
    }

    @Bindable
    public Long getId() {return id;}

    @Bindable
    public String getName() {return name;}

    @Bindable
    public String getImageUrl()  {return imageUrl;}

    @Bindable
    public String getCreatedAt() {
        return createdAt;
    }
    @Bindable
    public String getModifiedAt() {
        return modifiedAt;
    }




}
