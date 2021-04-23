package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListAndAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listandalarm);
//뒤로가기
        Button back_btn3 = (Button) findViewById(R.id.btnbk3);
        back_btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChooseListActivity.class);
                startActivity(intent);
            }
        });
//재능교환완료
        Button Finish_btn = (Button) findViewById(R.id.Fin);
        Finish_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //알림설정 버튼
        Button imageButton = (Button) findViewById(R.id.goAlarm);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SetAlarmActivity.class);
                startActivity(intent);
            }
        });

    }

}