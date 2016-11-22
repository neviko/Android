package com.example.ross.ex2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class ImageRate implements Parcelable
{

    private Bitmap bm;
    private float ratingStars;


    //constructor
    public ImageRate(Bitmap bm, float rs)
    {
        this.bm=bm;
        this.ratingStars = rs;
    }

    protected ImageRate(Parcel in) {
        bm = in.readParcelable(Bitmap.class.getClassLoader());
        ratingStars = in.readFloat();
    }


    public static final Creator<ImageRate> CREATOR = new Creator<ImageRate>() {
        @Override
        public ImageRate createFromParcel(Parcel in) {
            return new ImageRate(in);
        }

        @Override
        public ImageRate[] newArray(int size) {
            return new ImageRate[size];
        }
    };

    //getters and setters
    public Bitmap getBitMap()
    {
        return this.bm;
    }

    public void setBitMap(Bitmap bitMap)
    {
        this.bm=bitMap;
    }

    public float GetRateStars()
    {
        return this.ratingStars;
    }

    public void setRateStars(float num)
    {
        this.ratingStars=num;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bm, flags);
        dest.writeFloat(ratingStars);
    }

}
