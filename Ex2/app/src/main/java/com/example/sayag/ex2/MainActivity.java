package com.example.sayag.ex2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //fields
    private Button btn;
    private TextView txt;
    private Switch swc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn = (Button)findViewById(R.id.button);
        this.txt = (TextView)findViewById(R.id.textView2);
        this.swc = (Switch)findViewById(R.id.switch1);

        init();
    }


    public void init()
    {
        btn.setEnabled(false);
        btn.setText("Shoot");
        txt.setVisibility(View.INVISIBLE);
        swc.setChecked(false);
    }



    public void click (View v)
    {
        txt.setText("B O O M !");
        txt.setTextColor(Color.RED);
        txt.setVisibility(View.VISIBLE);


    }

    public void swichActivity (View v)
    {
       if( swc.isChecked() == true)
       {
           btn.setEnabled(true);

       }



        else
           init();
    }
}
