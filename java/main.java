package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class main extends AppCompatActivity {
    public static final int REQUEST_CODE_TAKER = 100;
    public static final int REQUEST_CODE_MESSAGE = 200;

    private ImageView iv_taker;
    private ImageView iv_message;
    private Button btn_send;
    private String tv_name;
    private String tv_number;
    private String tv_message;

    // 수신 받음
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_TAKER) {
                Cursor cursor = null;

                if (data != null) {
                    cursor = getContentResolver().query(
                            data.getData(),
                            new String[] {
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                    ContactsContract.CommonDataKinds.Phone.NUMBER},
                            null,
                            null,
                            null);
                }
                if (cursor != null) {
                    cursor.moveToFirst();

                    tv_name = cursor.getString(0);
                    tv_number = cursor.getString(1);

                    cursor.close();
                }
            } else if (requestCode == REQUEST_CODE_MESSAGE) {
                tv_message = data.getStringExtra("message");
            }
        }
    }

    // 메인 화면
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        // 권한 허용
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("잠깐! 디바이스와 정보 전달을 위한 권한을 허용시켜주세요!")
                .setDeniedMessage("거절됨")
                .setPermissions(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS
                ).check();

        // 받는 사람
        iv_taker = (ImageView)findViewById(R.id.taker);
        iv_taker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_TAKER);
            }
        });

        // 메세지
        iv_message = (ImageView)findViewById(R.id.message);
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),message.class);
                startActivityForResult(intent, REQUEST_CODE_MESSAGE);
            }
        });

        // 전송
        btn_send = findViewById(R.id.send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main.this,last_check.class);
                intent.putExtra("taker", tv_name);
                intent.putExtra("number", tv_number);
                intent.putExtra("message", tv_message);
                startActivity(intent);
            }
        });
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(), "권한 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "권한 거부됨", Toast.LENGTH_SHORT).show();
        }
    };
}