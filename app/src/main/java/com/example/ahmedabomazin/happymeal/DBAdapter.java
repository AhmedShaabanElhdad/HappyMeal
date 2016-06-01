package com.example.ahmedabomazin.happymeal;

/**
 * Created by Ahmed AboMazin on 5/14/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {
    static final String KEY_firstname = "firstname";
    static final String KEY_middlename = "middlename";
    static final String KEY_lastname = "lastname";

    static final String KEY_gendor = "gendor";
    static final String KEY_email = "email";
    static final String KEY_contact = "contact";

    static final String KEY_city = "city";
    static final String KEY_address = "address";
    static final String KEY_username= "username";
    static final String KEY_password= "password";
    static final String KEY_uid= "uid";


    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "person";
    static final int DATABASE_VERSION = 1;




    static final String DATABASE_CREATE ="create table person (firstname text not null,middlename text not null,lastname text not null,gendor text not null,"
            + "email text not null, contact text not null,city text not null, address text not null,username text not null,password text not null,uid text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS person");
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    //---insert a contact into the database---
    public long insertContact(String firstname, String middlename,String lastname, String gendor,String email, String contact,String city, String address,String username,String password,String uid)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_firstname, firstname);
        initialValues.put(KEY_middlename, middlename);
        initialValues.put(KEY_lastname, lastname);
        initialValues.put(KEY_gendor, gendor);
        initialValues.put(KEY_email, email);
        initialValues.put(KEY_contact, contact);
        initialValues.put(KEY_city, city);
        initialValues.put(KEY_address, address);
        initialValues.put(KEY_username, username);
        initialValues.put(KEY_password, password);
        initialValues.put(KEY_uid, uid);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    //---retrieves a particular contact---
    public Cursor getusername(String username) throws SQLException
    {Cursor mCursor =
            db.query(true, DATABASE_TABLE, new String[] {KEY_username}, KEY_username + "='" + username+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getusername(String uid,String password) throws SQLException
    {Cursor mCursor =
            db.query(true, DATABASE_TABLE, new String[] {KEY_username,KEY_uid}, KEY_uid + "='" + uid+"' and "+KEY_password+"='"+password+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }




}