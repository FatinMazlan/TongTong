package com.example.fatin.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class TryCreate extends AppCompatActivity {

    int register;
    ListView ListView01;
    Menu menu;

    //use polymorphism to access DataHelper
    DataHelper dbcenter;

    //to access class CreateBio, UpdateBio, and ViewBio
    public static TryCreate ma;


    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2, btnAdd;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TryCreate.this, TryParticipant.class);
                startActivity(intent);
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshListParticipant();

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into biodata values ('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "')");
                String g_name = text1.getText().toString();

                cursor = db.rawQuery("SELECT rowid FROM biodata where name = '"+g_name+"' ", null);
                int x = 0;
                while (cursor.moveToNext()) {
                    x = cursor.getInt(0);
                    for(int i = 0; i<TryParticipant.arrayPname.size(); i++){
                        db.execSQL("insert into participant values ('" +
                                TryParticipant.arrayPname.get(i) + "','" +
                                TryParticipant.arrayPemail.get(i) + "','" +
                                x + "')");
                    }

                    Toast.makeText(getApplicationContext(), "Data Successfully Added", Toast.LENGTH_SHORT).show();
                    MainActivity.ma.RefreshList();
                    finish();
                }
                TryParticipant.arrayPname.clear();
                TryParticipant.arrayPemail.clear();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub 0139324243  0176942678
                finish();
            }
        });
    }

    public void RefreshListParticipant() {
        ListView01 = (ListView) findViewById(R.id.listViewParticipant);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, TryParticipant.arrayPname));
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();

    }
}