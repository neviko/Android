package com.example.ross.ex3_home;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;


public class MainActivity extends Activity {


    private EditText name;
    private EditText phoneNumber;
    private Button insertBtn;
    private Button searchBtn;
    private  MyCursorAdapter mca;
    private SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = (EditText)findViewById(R.id.nameEditText);
        this.phoneNumber = (EditText)findViewById(R.id.phoneEditText2);
        this.insertBtn = (Button)findViewById(R.id.insertBtn);
        this.searchBtn = (Button)findViewById(R.id.searchBtn);

        MyDbHelper dbHelper = new MyDbHelper(this);
        ListView lvItems = (ListView)findViewById(R.id.ls1);

        SQLiteDatabase db1 = dbHelper.getReadableDatabase();
        Cursor c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME,null,null,null,null,null,null);
        mca = new MyCursorAdapter(this,c);
        lvItems.setAdapter(mca);


    }


    public void onInsertClick(View view)
    {
        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.

        ContentValues values = new ContentValues();

        values.put(com.example.ross.ex3_home.Contacts.CONTACT_NAME, this.name.getText().toString());
        values.put(com.example.ross.ex3_home.Contacts.PHONE_NUMBER, this.phoneNumber.getText().toString());

        long id;
        id=db.insert(com.example.ross.ex3_home.Contacts.TABLE_NAME,null,values); // write the object to DB


        db1= dbHelper.getReadableDatabase();
        Cursor c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME,null,null,null,null,null,null);
        mca.changeCursor(c);
    }




    public void onSearchClick(View view)
    {
        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.

        String []tableColumns= {com.example.ross.ex3_home.Contacts.CONTACT_NAME, com.example.ross.ex3_home.Contacts.PHONE_NUMBER};

        String whereClause = tableColumns[0]+" = ? OR "+ tableColumns[1]+" = ?";
        String[] whereArgs = new String[] {name.getText().toString(),phoneNumber.getText().toString()};
        String orderBy = tableColumns[0];

        Cursor c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME,tableColumns,whereClause,whereArgs,null,null,orderBy);
       // Cursor c = db.query(dbHelper.DATABASE_NAME," "+columns,columns[0]+" LIKE "+name +" AND "+columns[1]+" LIKE "+phoneNumber,null,null,null,null);
        mca.changeCursor(c);


        //str1.toLowerCase().contains(name.getText().toString().toLowerCase())
    }

}
