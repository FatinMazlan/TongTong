package com.example.fatin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Fatin on 15/12/2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    //Database Name
    private static final String DATABASE_NAME = "tong3.db";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Create Constructor for Data Helper

    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    //Create Table
    public void onCreate(SQLiteDatabase db){
        String sql = "create table biodata(name text not null, detail text not null);";
        db.execSQL(sql);

        String sql2 = "create table participant(name text not null, phone integer not null, p_key integer not null);";
        db.execSQL(sql2);

        String sql3 = "create table item(name text not null, amount integer not null, paid_by text not null, p_key integer not null);";
        db.execSQL(sql3);
    }

    //create method to upgrade database version if database exist
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
