package com.example.sayag.ex3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private TextView txtView;
    TextToSpeech tts;
    private String str;
    private Button voiceBtn;
    public RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        this.txtView = (TextView)findViewById(R.id.textView2);
        //rl = (RelativeLayout)findViewById(R.id.content_main2); // the RelativeLayout.xml object
        rl = (RelativeLayout)findViewById(R.id.content_main2);
        this.voiceBtn = (Button)findViewById(R.id.button2);




        Intent i = getIntent(); // get data from the first activity with Instant
        str = i.getStringExtra("text");
        txtView.setText(str);




        // get default settings if dose'nt choose any radio button
        SharedPreferences settings = getSharedPreferences("myFile",MODE_PRIVATE);
        boolean defValue = settings.getBoolean("isPink",false);


        //set the bg color from the default settings
        if(defValue)
            rl.setBackgroundColor(Color.RED);

        else
            rl.setBackgroundColor(Color.BLUE);



        tts = new TextToSpeech (this,new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status)
            {
                if(status != TextToSpeech.ERROR)
                    tts.setLanguage(Locale.US);
            }


        } );




    }



    @Override
    protected void onStart()
    {



        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void voiceBtn(View view)
    {
        if(view.getId()== voiceBtn.getId())
            tts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
    }
}

