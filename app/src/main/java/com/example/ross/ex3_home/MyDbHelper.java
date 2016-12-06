package com.example.ross.ex3_home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;


///**
// * Created by Ross on 12/2/2016.
// */
public class MyDbHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "contacts.db";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + Contacts.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES= "CREATE TABLE " + Contacts.TABLE_NAME + " (" +Contacts._ID +
            " INTEGER PRIMARY KEY," + Contacts.CONTACT_NAME + " TEXT NOT NULL, "
            + Contacts.PHONE_NUMBER +
            " NUMBER" + ");";


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL();
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
