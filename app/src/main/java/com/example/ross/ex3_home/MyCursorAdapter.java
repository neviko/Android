package com.example.ross.ex3_home;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.R.attr.id;

/**
 * Created by Ross on 12/2/2016.
 */

public class MyCursorAdapter extends CursorAdapter
{
    LayoutInflater inflater;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
        inflater = LayoutInflater.from(context);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return inflater.inflate(R.layout.my_custom_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView name = (TextView)view.findViewById(R.id.txtContactName);
        TextView phone = (TextView)view.findViewById(R.id.txtContactPhone);
        ImageButton img = (ImageButton)view.findViewById(R.id.callImg);

        name.setText(cursor.getString(cursor.getColumnIndex(
                Contacts.CONTACT_NAME)));



        if ( ! (cursor.getString(cursor.getColumnIndex(Contacts.PHONE_NUMBER))).equals("")) // if the contact have a phone number
        {
            img.setBackgroundResource(android.R.drawable.sym_action_call); // set a new image
            //img.setImageResource(android.R.drawable.sym_action_call);
            phone.setText(cursor.getString(cursor.getColumnIndex(Contacts.PHONE_NUMBER))); // set phone number

        }

    }


}
