package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_list);
//뒤로가기버튼
        Button backchat = (Button) findViewById(R.id.btnbk2);
        backchat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
        //체크리스트 설정이동버튼
        Button goCheckList = (Button) findViewById(R.id.set);
        goCheckList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
//설정완료버튼
        Button finChoice = (Button) findViewById(R.id.finSet);
        finChoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListAndAlarmActivity.class);
                startActivity(intent);
            }
        });
    }
}