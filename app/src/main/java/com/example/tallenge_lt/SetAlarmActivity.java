package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class SetAlarmActivity extends AppCompatActivity {

    ImageButton bt_home,bt_chat,bt_alarm,bt_mypage;
    Button bt_back;
    AlarmData alarmData = new AlarmData();
    CheckBox sun,mon,tue,wed,thu,fri,sat;
//    FirebaseDatabase database;
    DatabaseReference myRef;

    String emailId, IdToken, user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

        // MainActivity로 이동
        bt_home = (ImageButton) findViewById(R.id.home);
        bt_chat = (ImageButton) findViewById(R.id.chat);
        bt_alarm = (ImageButton) findViewById(R.id.alarm);
        bt_mypage = (ImageButton) findViewById(R.id.mypage);

        bt_back = (Button) findViewById(R.id.btn_back);

        emailId = getIntent().getStringExtra("emailId");
        IdToken = getIntent().getStringExtra("idToken");
        user = getIntent().getStringExtra("user");


        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("idToken", IdToken);
                intent.putExtra("emailId", emailId);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("idToken", IdToken);
                intent.putExtra("emailId", emailId);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("idToken", IdToken);
                intent.putExtra("emailId", emailId);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        bt_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
                intent.putExtra("idToken", IdToken);
                intent.putExtra("emailId", emailId);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        bt_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
                intent.putExtra("idToken", IdToken);
                intent.putExtra("emailId", emailId);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }


}