package com.example.ahmedabomazin.happymeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Ahmed AboMazin on 5/20/2016.
 */
public class DabaaDBadapter
{
    static final String KEY_ID = "_id";
    static final String KEY_TYPE = "type";
    static final String KEY_ADDRESS = "address";
    static final String KEY_DATE = "date";
    static final String KEY_PRICE = "price";




    static final String TAG = "OrderDBadapter";


    static final String DATABASE_NAME = "MyNewDB2";
    static final String DATABASE_TABLE = "Dabbaorders";
    static final int DATABASE_VERSION = 1;


    static final String DATABASE_CREATE = "create table  if not exists Dabbaorders (_id text not null,type text not null ,address text not null,date text not null,price text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DabaaDBadapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + "to" + newVersion + ",which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Dabbaorders");
            onCreate(db);
        }
    }

    //---opens the database---
    public DabaaDBadapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertContact(String id,String type,String data,String address,String price) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_TYPE, type);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_DATE, data);
        initialValues.put(KEY_PRICE, price);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }



    //---retrieves a particular contact---
    public Cursor getContact(String rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[]{KEY_ID, KEY_TYPE,KEY_DATE,KEY_ADDRESS,KEY_PRICE}, KEY_ID + " ='"+rowId+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_TYPE, KEY_DATE, KEY_ADDRESS,KEY_PRICE}, null, null, null, null, null);
    }
}
