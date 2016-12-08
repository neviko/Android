package com.example.ross.ex3_home;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{


    private EditText name;
    private EditText phoneNumber;
    private Button insertBtn;
    private Button searchBtn;
    private MyCursorAdapter mca;
    private SQLiteDatabase db1;
    Cursor c;
    ListView lvItems;
    String phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context =getApplicationContext();

        this.name = (EditText) findViewById(R.id.nameEditText);
        this.phoneNumber = (EditText) findViewById(R.id.phoneEditText2);
        this.insertBtn = (Button) findViewById(R.id.insertBtn);
        this.searchBtn = (Button) findViewById(R.id.searchBtn);

        MyDbHelper dbHelper = new MyDbHelper(this);
        lvItems = (ListView) findViewById(R.id.lv1);


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id)
            {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String phoneTxt = cursor.getString(cursor.getColumnIndexOrThrow(com.example.ross.ex3_home.Contacts.PHONE_NUMBER));

                if(phoneTxt.equals(""))
                {

                    return;
                }


                else
                {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+phoneTxt));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);

                }
                phoneTxt="";
            }
        });


        SQLiteDatabase db1 = dbHelper.getReadableDatabase();
        c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);
        mca = new MyCursorAdapter(this, c);
        lvItems.setAdapter(mca);



    }


    public void onInsertClick(View view)
    {
        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.

        String contactName= this.name.getText().toString();
        String contactPhone = this.phoneNumber.getText().toString();

        ContentValues values = new ContentValues();

        if(contactName.equals(""))
        {
            Toast.makeText(this, "Contact name can't be empty, please fill contact name",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(isNumber(contactPhone) ==false)
        {
            Toast.makeText(this, "Phone number must contain only digits", Toast.LENGTH_LONG).show();
            return;
        }




        values.put(com.example.ross.ex3_home.Contacts.CONTACT_NAME, contactName);
        values.put(com.example.ross.ex3_home.Contacts.PHONE_NUMBER, contactPhone);

        long id;
        id = db.insert(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, values); // write the object to DB


        db1 = dbHelper.getReadableDatabase();
        Cursor c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);
        mca.changeCursor(c);
    }


    public void onSearchClick(View view) {
        Cursor c;

        String contactName= this.name.getText().toString();
        String contactPhone = this.phoneNumber.getText().toString();


        if(isNumber(contactPhone) ==false)
        {
            Toast.makeText(this, "Phone number must contain only digits", Toast.LENGTH_LONG).show();
            return;
        }

        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.


        String[] tableColumns = {com.example.ross.ex3_home.Contacts.CONTACT_NAME, com.example.ross.ex3_home.Contacts.PHONE_NUMBER};

        String nameAndPhoneWhereClause = tableColumns[0] + " LIKE '%"+contactName+"% AND " + tableColumns[1] + " LIKE '%"+contactPhone+"%'";
        String[] nameAndPhoneWhereArgs = new String[]{name.getText().toString(), phoneNumber.getText().toString()};

        String nameWhereClause = tableColumns[0] + " LIKE '%"+contactName+"%'";
        //String[] nameWhereArgs = {name.getText().toString()};

        String PhoneWhereClause = tableColumns[1] + " LIKE '%"+contactPhone+"%'";
        String[] phoneWhereArgs = {phoneNumber.getText().toString()};
//        String phoneWhereClause = tableColumns[1]+" = ? ";


        String orderBy = tableColumns[0];


        if (name.getText().toString().equals("") && phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);

        else if (!name.getText().toString().equals("") && phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, nameWhereClause, null, null, null, null);

        else if (name.getText().toString().equals("") && !phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, PhoneWhereClause, null, null, null, null);


        else
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, nameAndPhoneWhereClause, nameAndPhoneWhereArgs, null, null, null);
        // Cursor c = db.query(dbHelper.DATABASE_NAME," "+columns,columns[0]+" LIKE "+name +" AND "+columns[1]+" LIKE "+phoneNumber,null,null,null,null);

        mca.changeCursor(c);


    }



    public boolean isNumber(String str)
    {
        if(str.equals(""))
            return true;

        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            Toast.makeText(this, "Contact name can't be empty, please fill contact name", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }
}