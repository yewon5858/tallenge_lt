package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListSetActivity extends AppCompatActivity {
    //체크리스트 분야설정 UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listset);
        //뒤로가기버튼
        Button bck_btn = (Button) findViewById(R.id.bck_btn);
        bck_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChooseListActivity.class);
                startActivity(intent);
            }
        });
    }
}