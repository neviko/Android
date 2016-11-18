package com.example.ross.ex2;

import android.graphics.Bitmap;


public class ImageRate
{

    private Bitmap bm;
    private float ratingStars;


    //constructor
    public ImageRate(Bitmap bm, float rs)
    {
        this.bm=bm;
        this.ratingStars = rs;
    }

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

}
