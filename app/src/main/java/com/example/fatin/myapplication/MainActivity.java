package com.example.fatin.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int x;
    String[] register;
    ListView ListView01;
    Menu menu;

    //access Database
    protected Cursor cursor;

    //use polymorphism to access DataHelper
    DataHelper dbcenter;

    //to access class CreateBio, UpdateBio, and ViewBio
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, TryCreate.class);
                startActivity(intent);
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        register = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            register[cc] = cursor.getString(0).toString();
        }
        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, register));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = register[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Add Item", "Delete Group"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Selection");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                        cursor = db.rawQuery("SELECT rowid FROM biodata where name = '"+selection+"' ", null);
                        x = 0;
                        while (cursor.moveToNext()) {
                            x = cursor.getInt(0);

                            switch (item) {
                                case 0:
                                    Intent in = new Intent(getApplicationContext(), AddItemActivity.class);
                                    in.putExtra("name", selection);
                                    startActivity(in);
                                    break;
                                case 1:
                                    db = dbcenter.getWritableDatabase();
                                    db.execSQL("delete from biodata where name = '" + selection + "'");
                                    db.execSQL("delete from participant where p_key = '" + x + "'");

                                    Toast.makeText(getApplicationContext(), "Data successfully deleted", Toast.LENGTH_SHORT).show();

                                    RefreshList();
                                    break;
                            }
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}