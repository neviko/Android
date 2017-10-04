package com.example.ross.baby_saver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    private String fullname;
    private String email;
    private String password;
    private EditText emailEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login_);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);


    }



    public void connect(View v){
//        isRegisterd = getIntent().getExtras().getBoolean("bool");
        SharedPreferences preferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
            email  = preferences.getString("Email","there is not such user");
            password = preferences.getString("Password","there is no such a password");
            fullname = preferences.getString("fullname","אנונימי");

            if(emailEditText.getText().toString().equals(email) && passwordEditText.getText().toString().equals(password)){
                Toast.makeText(this," ברוך הבא " +  fullname, Toast.LENGTH_LONG).show();
                Intent homePage = new Intent(this,Home_Page_Activity.class);
                startActivity(homePage);
            }else{
                AlertDialog.Builder error = new AlertDialog.Builder(this);
                error.setMessage("שם המשתמש או הסיסמא אינם נכונים");
                error.setTitle("שגיאת כניסה למערכת");
                error.setPositiveButton("אישור",null);
                error.setCancelable(true);
                error.create().show();

            }




    }


    public void register(View v){
        Toast.makeText(this, "register", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,Register_Activity.class);
        startActivity(i);



    }




}
