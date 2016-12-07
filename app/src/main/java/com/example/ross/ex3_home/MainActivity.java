package com.example.ross.ex3_home;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {


    private EditText name;
    private EditText phoneNumber;
    private Button insertBtn;
    private Button searchBtn;
    private MyCursorAdapter mca;
    private SQLiteDatabase db1;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = (EditText) findViewById(R.id.nameEditText);
        this.phoneNumber = (EditText) findViewById(R.id.phoneEditText2);
        this.insertBtn = (Button) findViewById(R.id.insertBtn);
        this.searchBtn = (Button) findViewById(R.id.searchBtn);

        MyDbHelper dbHelper = new MyDbHelper(this);
        ListView lvItems = (ListView) findViewById(R.id.ls1);

        SQLiteDatabase db1 = dbHelper.getReadableDatabase();
        c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);
        mca = new MyCursorAdapter(this, c);
        lvItems.setAdapter(mca);


    }


    public void onInsertClick(View view) {
        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.

        ContentValues values = new ContentValues();

        values.put(com.example.ross.ex3_home.Contacts.CONTACT_NAME, this.name.getText().toString());
        values.put(com.example.ross.ex3_home.Contacts.PHONE_NUMBER, this.phoneNumber.getText().toString());

        long id;
        id = db.insert(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, values); // write the object to DB


        db1 = dbHelper.getReadableDatabase();
        Cursor c = db1.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);
        mca.changeCursor(c);
    }


    public void onSearchClick(View view) {
        Cursor c;

        MyDbHelper dbHelper = new MyDbHelper(this); // Create pointer to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Open writable access.


        String[] tableColumns = {com.example.ross.ex3_home.Contacts.CONTACT_NAME, com.example.ross.ex3_home.Contacts.PHONE_NUMBER};

        String nameAndPhoneWhereClause = tableColumns[0] + " = ? AND " + tableColumns[1] + " = ?";
        String[] nameAndPhoneWhereArgs = new String[]{name.getText().toString(), phoneNumber.getText().toString()};

        String nameWhereClause = tableColumns[0] + " = ? ";
        String[] nameWhereArgs = {name.getText().toString()};

        String PhoneWhereClause = tableColumns[1] + " = ?";
        String[] phoneWhereArgs = {phoneNumber.getText().toString()};
//        String phoneWhereClause = tableColumns[1]+" = ? ";


        String orderBy = tableColumns[0];


        if (name.getText().toString().equals("") && phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, null, null, null, null, null);

        else if (!name.getText().toString().equals("") && phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, nameWhereClause, nameWhereArgs, null, null, null);

        else if (name.getText().toString().equals("") && !phoneNumber.getText().toString().equals(""))
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, PhoneWhereClause, phoneWhereArgs, null, null, null);


        else
            c = db.query(com.example.ross.ex3_home.Contacts.TABLE_NAME, null, nameAndPhoneWhereClause, nameAndPhoneWhereArgs, null, null, null);
        // Cursor c = db.query(dbHelper.DATABASE_NAME," "+columns,columns[0]+" LIKE "+name +" AND "+columns[1]+" LIKE "+phoneNumber,null,null,null,null);


        mca.changeCursor(c);


        //str1.toLowerCase().contains(name.getText().toString().toLowerCase())
    }


    public void onImageClick(View view)
    {
//        int rowPosition = (Integer)view.getTag();
//       // mca.getItem(position);
        TextView txt1= (TextView)view.findViewById(R.id.txtContactPhone);
//        //view.getTag();
       String phoneTxt= txt1.getText().toString();
        //TextView phoneTxt = (TextView)findViewById(R.id.txtContactPhone);

        if(phoneTxt.equals(""))
            return;


        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneTxt));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

}
