package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class loop extends AppCompatActivity {
    SmsManager smsManager = SmsManager.getDefault();
    String number;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        Intent intent = getIntent();
        number = intent.getStringExtra("number");
        message = intent.getStringExtra("message");
        String my_num = "01045861420";
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                smsManager.sendTextMessage(my_num, null, message, null, null);
            }
        };
        timer.schedule(timerTask,0,1000);

        Button btn_end = findViewById(R.id.btn_end);
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerTask.cancel();
                finish();
            }
        });
    }
}