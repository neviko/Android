package com.example.ross.ex2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button nextBtn;
    private Button submitBtn;
    private RatingBar stars;
    private ImageView imageToSubmit;
    private Bitmap imageBitmap;
    private ArrayList<ImageRate> imageArrList;
    private int counter;
    private int imageCounter;
    int arrayListCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        stars = (RatingBar)findViewById(R.id.ratingBar);
        imageToSubmit = (ImageView)findViewById(R.id.imageView);
        arrayListCounter =0;
        this.imageCounter = 0;
        imageArrList = new ArrayList<ImageRate>();
    }


    public void openCamera(View v)
    {
        Intent takePicIntante = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // create a new intent for imageToSubmit transfer between activities.
        if(takePicIntante.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePicIntante,REQUEST_IMAGE_CAPTURE);// callback function
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) // override the result function must implement with startActivityForResult
    {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap)extras.get("data");
            imageToSubmit.setImageBitmap(imageBitmap);

        }
    }





    //encoding and decoding imageToSubmit to string function if we dont want to store the imageToSubmit data on a filesystem,
    // we can just store the base64 string in a database instead.

    private String bitmapToBase64(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    public void onSubmit(View v)
    {

        if(imageBitmap==null){
            Toast.makeText(MainActivity.this,"Please take a picture before submitting",Toast.LENGTH_LONG).show();
            return;
        }

        float rate = stars.getRating(); // get the user rate
        ImageRate ir= new ImageRate(imageBitmap,rate);


        imageArrList.add(arrayListCounter,ir);//adding an object to array
        arrayListCounter++;
        imageCounter++;
        imageBitmap=null;

        Toast.makeText(MainActivity.this,"Your imageToSubmit have been successfully submitted",Toast.LENGTH_LONG).show();

    }



    public void onNext(View v)
    {
        Intent i = new Intent(this,Main2Activity.class);
        i.putParcelableArrayListExtra("myList",imageArrList);

        startActivity(i);// launch Main2activity with i intent


    }


}



