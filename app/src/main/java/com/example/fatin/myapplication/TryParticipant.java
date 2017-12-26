package com.example.fatin.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fatin on 18/12/2017.
 */

public class TryParticipant extends AppCompatActivity{

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    public static ArrayList<String> arrayPname = new ArrayList<String>();
    public static ArrayList<String> arrayPemail = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                String inputName = text1.getText().toString();
                String inputEmail = text2.getText().toString();

                if(arrayPname.contains(inputName) && arrayPemail.contains(inputEmail)){
                    Toast.makeText(getApplicationContext(), "Participant Exist", Toast.LENGTH_SHORT).show();
                }
                else if(arrayPemail.contains(inputEmail)){
                    Toast.makeText(getApplicationContext(), "Participant Exist", Toast.LENGTH_SHORT).show();
                }
                else if(inputName == null || inputEmail == null || inputName.trim().equals("") || inputEmail.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Input Field is Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    arrayPname.add(inputName);
                    arrayPemail.add(inputEmail);
                    Toast.makeText(getApplicationContext(), "Participant Added", Toast.LENGTH_SHORT).show();
                    TryCreate.ma.RefreshListParticipant();
                    finish();
                }
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}