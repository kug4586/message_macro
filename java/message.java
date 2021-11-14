package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class message extends AppCompatActivity {

    Button btn_back;
    Button btn_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        EditText et_message = findViewById(R.id.et_message);

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
                String message = et_message.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("message", message);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}