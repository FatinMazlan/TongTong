package com.example.fatin.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Fatin on 20/12/2017.
 */

public class OweActivity extends AppCompatActivity {

    String[] register, reg, reg2;

    //access Database
    protected Cursor cursor, cursor2;
    // use polymorphism to access DataHelper

    DataHelper dbcenter;
    EditText text1, text2;

    ListView ListView01;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_owe);

        btn1 = (Button) findViewById(R.id.payable);
        btn2 = (Button) findViewById(R.id.receivable);
        btn3 = (Button) findViewById(R.id.payable1);
        btn4 = (Button) findViewById(R.id.payable2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(OweActivity.this, ReceiveMoneyActivity.class);
                        startActivity(intent);
                    }
                });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(OweActivity.this, SetReminder.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(OweActivity.this, SetReminder.class);
                startActivity(intent);
            }
        });
            }

}
