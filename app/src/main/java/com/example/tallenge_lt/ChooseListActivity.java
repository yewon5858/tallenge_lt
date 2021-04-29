package com.example.tallenge_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseListActivity extends AppCompatActivity {
    //변수 선언
    private ListView exchangeListView;
    private ExchageAdapter adapter;
    private List<ExchangeList> exchangeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_list);
        //교환리스트 구현
        exchangeListView = (ListView) findViewById(R.id.listview1);
        exchangeList=new ArrayList<ExchangeList>();
        exchangeList.add(new ExchangeList("C언어"));
        exchangeList.add(new ExchangeList("중국어"));
        exchangeList.add(new ExchangeList("영어"));
        exchangeList.add(new ExchangeList("일본어"));
        adapter=new ExchageAdapter(getApplicationContext(),exchangeList);
        exchangeListView.setAdapter(adapter);




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