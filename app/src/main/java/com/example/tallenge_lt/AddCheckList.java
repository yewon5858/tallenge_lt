package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//새로운 체크리스트 추가 UI
public class AddCheckList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchecklist);
        Button btn_back2 = (Button) findViewById(R.id.bck_btn2);
        btn_back2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
        Button btn_complt = (Button) findViewById(R.id.complt);
        btn_complt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
    }

}