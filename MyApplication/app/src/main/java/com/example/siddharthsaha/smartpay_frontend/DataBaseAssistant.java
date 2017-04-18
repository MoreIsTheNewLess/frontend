package com.example.siddharthsaha.smartpay_frontend;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Siddharth Saha on 4/1/2017.
 */

public class DataBaseAssistant extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "accounts.db";
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_VPA = "VPA";
    private static final String COLUMN_ADDRESS = "address";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table accounts (id integer primary key not null , " + "name text not null , email text not null , uname text not null , pass text not null, VPA text not null, address text not null);" ;

    public DataBaseAssistant(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }

        return b;
    }

    public String getFullName(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, name from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(uname)) {
                    b = cursor.getString(1);

                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    public String getEmail(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, email from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }
    public String getVPA(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, VPA from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    public String getAddress(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, address from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }
//    public String getType(String uname) {
//        db = this.getReadableDatabase();
//        String query = "select uname, type from " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query, null);
//        String a,b;
//        b = "not found";
//        if(cursor.moveToFirst()) {
//            do {
//                a = cursor.getString(0);
//                if(a.equals(uname)) {
//                    b = cursor.getString(1);
//                    break;
//                }
//            }while(cursor.moveToNext());
//        }
//        return b;
//    }


    public void insertAccount(Account acc) {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, acc.getName());
        values.put(COLUMN_EMAIL, acc.getEmail());
        values.put(COLUMN_UNAME, acc.getUsername());
        values.put(COLUMN_PASS, acc.getPassword());
        values.put(COLUMN_VPA, acc.getVPA());
        values.put(COLUMN_ADDRESS, acc.getAddress());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
