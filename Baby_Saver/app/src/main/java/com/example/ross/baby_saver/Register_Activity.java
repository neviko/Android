package com.example.ross.baby_saver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {
    private EditText fullname,passwd,email,recPasswd,nannyAddress,phoneNum,childName;
    private Button registerBtn;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        fullname = (EditText)findViewById(R.id.fullNameEditText);
        passwd = (EditText)findViewById(R.id.passwdEditText);
        recPasswd = (EditText)findViewById(R.id.confirmPasswdEditText);
        email = (EditText)findViewById(R.id.emailText);
        nannyAddress = (EditText)findViewById(R.id.nannyAddressEditText);
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        childName = (EditText)findViewById(R.id.ChildName);

        registerBtn = (Button)findViewById(R.id.registerBtn);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



    }


    public void registerPressed(View v){

       boolean isValid =  checkValidation();

        if(isValid){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email",email.getText().toString());
            editor.putString("Password",passwd.getText().toString());
            editor.putString("nannyAddress",nannyAddress.getText().toString());
            editor.putString("childName",childName.getText().toString());
            editor.putString("phoneNum",phoneNum.getText().toString());
            editor.putString("fullname",fullname.getText().toString());

            editor.commit();
            Toast.makeText(this, " תודה " + fullname.getText().toString() +" - המידע שלך נשמר בהצלחה ", Toast.LENGTH_LONG).show();

            sendToLogin();


        }

    }

    private void sendToLogin() {
        Intent login = new Intent(this,Login_Activity.class);
        startActivity(login);

    }


    private boolean checkValidation(){
        int counter =0;
        if(fullname.getText().toString().equals("")){
            fullname.setError("השדה הזה הינו חובה");
            counter++;
        }

        if(passwd.getText().toString().equals("")){
            passwd.setError("השדה הזה הינו חובה");
            counter++;
        }


        if(recPasswd.getText().toString().equals("")){
            recPasswd.setError("השדה הזה הינו חובה");
            counter++;
        }


        if(email.getText().toString().equals("")){
            email.setError("השדה הזה הינו חובה");
            counter++;
        }

        if(!(passwd.getText().toString().equals(recPasswd.getText().toString()))){
            passwd.setError("הסיסמאות אינן זהות");
            recPasswd.setError("הסיסמאות אינן זהות");
            counter++;
        }

        if(nannyAddress.getText().toString().equals("")){
            nannyAddress.setError("השדה הזה הינו חובה. אנו ממליצים להדליק GPS ולרשום כתובת מדוייקת");
            counter++;
        }

        if(childName.getText().toString().equals("")){
            childName.setError("השדה הזה הינו חובה. ");
            counter++;
        }

        if(!isNumeric(phoneNum.getText().toString()) || phoneNum.getText().toString().equals("")){
            if( phoneNum.getText().toString().equals("")){
                phoneNum.setError("השדה הזה הינו חובה. ");
            }
            else{
                phoneNum.setError("אנא הכנס מספר טלפון חוקי");
            }

            counter++;
        }



        if(counter>0){
            return false;
        }

        return true;
    }



    public boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }




}
