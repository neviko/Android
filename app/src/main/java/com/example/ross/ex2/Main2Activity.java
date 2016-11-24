package com.example.ross.ex2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main2Activity extends AppCompatActivity {
    private Bitmap[] bitMapArr;
    private ArrayList<ImageRate> imageArrList;
    private ImageView iv1,iv2,iv3;

    //private ImageRate[] imageRateArr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        iv1=(ImageView)findViewById(R.id.imageView1);
        iv2=(ImageView)findViewById(R.id.imageView2);
        iv3=(ImageView)findViewById(R.id.imageView3);


        // get the array list from the main activity
        Intent i = this.getIntent();
        imageArrList = i.getParcelableArrayListExtra("myList");



        if(imageArrList.size()>1) // if the array list have more then one items goto the sort command.
        {
            // Sorting the ArrayList by rating stars
            Collections.sort(imageArrList, new Comparator<ImageRate>()
            {
                @Override
                public int compare(ImageRate o1, ImageRate o2) {

                    Float change1 = Float.valueOf(o1.GetRateStars());
                    Float change2 = Float.valueOf(o2.GetRateStars());
                    return change1.compareTo(change2);
                }
            });
        }


        // presenting the top three rated images only if exist.
        if(imageArrList.size()>0)
            iv1.setImageBitmap(imageArrList.get(imageArrList.size()-1).getBitMap());

        if(imageArrList.size() > 1)
            iv2.setImageBitmap(imageArrList.get(imageArrList.size()-2).getBitMap());

        if(imageArrList.size() > 2)
            iv3.setImageBitmap(imageArrList.get(imageArrList.size()-3).getBitMap());


    }

    //decoding string to bitMap
    private Bitmap base64ToBitmap(String b64)
    {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }




}
