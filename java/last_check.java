package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class last_check extends AppCompatActivity {

    private Button btn_back;
    private Button btn_complete;
    private TextView Last_taker;
    private TextView Last_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_check);

        Last_taker = findViewById(R.id.Last_taker);
        Last_message = findViewById(R.id.Last_message);

        Intent intent = getIntent();
        String name = intent.getStringExtra("taker");
        String number = intent.getStringExtra("number");
        String message = intent.getStringExtra("message");

        Last_taker.setText("받는 이 : " + name + "(" + number + ")");
        Last_message.setText("보내는 말 :\n" + message);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_complete = findViewById(R.id.btn_complete);
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), loop.class);
                intent.putExtra("number", number);
                intent.putExtra("message", message);
                startActivity(intent);
                finish();
            }
        });
    }
}