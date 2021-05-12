package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//체크리스트 세부항목추가 UI
public class SetCheckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setchecklist);
        TextView textView=(TextView) findViewById(R.id.btn_chatitem);
        Intent intent =getIntent();
        textView.setText(intent.getStringExtra("checkTitle"));
        //뒤로가기
        Button back =(Button)findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
        //설정완료
        Button fin =(Button)findViewById(R.id.finish);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
    }
}