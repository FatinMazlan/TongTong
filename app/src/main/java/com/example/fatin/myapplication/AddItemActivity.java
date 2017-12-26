package com.example.fatin.myapplication;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    String[] register, reg, reg2;

    //access Database
    protected Cursor cursor, cursor2;
    // use polymorphism to access DataHelper

    DataHelper dbcenter;
    EditText text1, text2;

    ListView ListView01;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dbcenter = new DataHelper(this);
        RefreshList();

        ListView01 = (ListView) findViewById(R.id.listChooseParticipant);
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT rowid FROM biodata where name = '" + getIntent().getStringExtra("name") + "'", null);
        int x = 0;
        while (cursor.moveToNext()) {
            x = cursor.getInt(0);
            cursor2 = db.rawQuery("SELECT * FROM participant where p_key = '" + x + "'", null);
            register = new String[cursor2.getCount()];
            cursor2.moveToFirst();
            for (int cc = 0; cc < cursor2.getCount(); cc++) {
                cursor2.moveToPosition(cc);
                register[cc] = cursor2.getString(0).toString();
            }
        }
            ListView01 = (ListView) findViewById(R.id.listChooseParticipant);
            ListView01.setAdapter(new ArrayAdapter(AddItemActivity.this, android.R.layout.simple_list_item_1, register));
            ListView01.setSelected(true);

            ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                    final String selection = register[arg2]; //.getItemAtPosition(arg2).toString();
                    SQLiteDatabase db = dbcenter.getWritableDatabase();
                    cursor = db.rawQuery("SELECT p_key FROM participant where name = '" + selection + "' ", null);
                    int x = 0;
                    while (cursor.moveToNext()) {
                        x = cursor.getInt(0);
                    }
                    Toast.makeText(getApplicationContext(), "Item Successfully Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddItemActivity.this, OweActivity.class);
                    startActivity(intent);
                }
            });
            ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}