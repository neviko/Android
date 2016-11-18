package com.example.sayag.ex3;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    //fields
    private EditText userText;
    private RadioButton rdbBlue;
    private RadioButton rdbPink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get elements value by id
        this.userText=(EditText)findViewById(R.id.editText);
        this.rdbBlue = (RadioButton)findViewById(R.id.radioButtonBlue);
        this.rdbPink = (RadioButton)findViewById(R.id.radioButtonPink);

    }

    public void onBtnClick(View view)
    {
        boolean isPink=false;
        if(rdbBlue.isChecked() || rdbPink.isChecked() ) // if nothing choose take the default color
        {

            //set the bg color by rdb choose as by default
            SharedPreferences settings = getSharedPreferences("myFile",MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit(); // here i create a reference to the setting file


            if(rdbBlue.isPressed() || rdbBlue.isChecked())
            {
                editor.putBoolean("isPink",isPink); // here isPink is false
            }

            else if(rdbPink.isPressed()  || rdbPink.isChecked())
            {
                isPink=true;
                editor.putBoolean("isPink",isPink); // here isPink is true
            }

            editor.apply();
        }


        // passing arguments with Intent ,and start the nwe activity
        Intent i = new Intent(this,Main2Activity.class);
        i.putExtra("text",this.userText.getText().toString());
        i.putExtra("color",isPink);
        startActivity(i); // launch the new activity

    }

    public void onRdbClick(View v)
    {
        // only one radio button can be pressed
        if(this.rdbBlue.isPressed())
            this.rdbPink.setChecked(false);

        else if(this.rdbPink.isPressed())
            this.rdbBlue.setChecked(false);
    }



}
