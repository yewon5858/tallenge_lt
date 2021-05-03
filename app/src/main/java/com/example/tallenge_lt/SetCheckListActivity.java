package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//체크리스트 세부항목추가 UI
public class SetCheckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setchecklist);
        TextView textView=(TextView) findViewById(R.id.btn_chatitem);
        Intent intent =getIntent();
        textView.setText(intent.getStringExtra("title"));
    }
}