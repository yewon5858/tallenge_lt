package com.example.tallenge_lt;
//사용 xml exchangelist.xml / 자바 ExchangeList.java , ExchageAdapter.java (변수중복때문에 오타로했습니다!)
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChooseListActivity extends AppCompatActivity {
    //변수 선언
    private ListView exchangeListView;
    private ExchageAdapter adapter;
    private List<ExchangeList> exchangelist;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_list);
        //교환리스트 구현




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
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
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
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        myRef=database.getReference("tallenge").child("checklist");
        exchangeListView = (ListView) findViewById(R.id.listview1);
        exchangelist=new ArrayList<ExchangeList>();
        exchangelist.add(new ExchangeList("중국어"));
        exchangelist.add(new ExchangeList("C언어"));
        exchangelist.add(new ExchangeList("영어"));
        exchangelist.add(new ExchangeList("일본어"));
        adapter=new ExchageAdapter(getApplicationContext(),exchangelist);
        exchangeListView.setAdapter(adapter);

    }
}