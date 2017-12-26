package com.example.fatin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Fatin on 21/12/2017.
 */

public class ReceiveMoneyActivity extends AppCompatActivity {
    EditText text1, text2;

    ListView ListView01;
    Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receivemoney);

        btn1 = (Button) findViewById(R.id.payable1);
        btn2 = (Button) findViewById(R.id.payable2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                    String no = "0139324243";
                    String msg = "Hi there! You owed Fatin an amount of RM 15 for PizzaHut. Don't forget to pay her back. She needs money :) -via TongTong Apps-";

                    Intent intent = new Intent(getApplicationContext(),ReceiveMoneyActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(no, null, msg, pi, null);

                    Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();
                    }
                });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub 0139324243  0176942678
                String no = "0176942678";
                String msg = "Hi there! You owed Fatin an amount of RM 9 for Assignment Printing. Don't forget to pay her back. She needs money :) -via TongTong Apps-";

                Intent intent = new Intent(getApplicationContext(),ReceiveMoneyActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
