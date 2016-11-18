package com.example.sayag.ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Display;
import android.content.res.Configuration;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private EditText student_Name;
    private EditText student_Grade;
    private TextView txtList;
    private int grade;
    private Button add_button;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.student_Name = (EditText)findViewById(R.id.studentName);
        this.txtList = (TextView)findViewById(R.id.txtlist);
        this.student_Grade = (EditText)findViewById(R.id.studentGrade);
        this.add_button = (Button)findViewById(R.id.button);

        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.main);

    }

    public void addStudent(View v1){

        try
        {

            int grade = Integer.parseInt(student_Grade.getText().toString());
            String StudentName = student_Name.getText().toString();
            if(grade >= 0 && grade <= 100 && !StudentName.isEmpty())
                this.txtList.append("Student: "+student_Name.getText().toString() +"  Grade: "+ student_Grade.getText().toString()+"\n" );

            else
                createAlert("Please enter a valid name and a valid grade between 0 to 100");

        }

        catch(NumberFormatException nfe)
        {
            createAlert("Please enter a valid name and numeric grade");
        }




    }




    public void createAlert(String alert)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(alert);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
