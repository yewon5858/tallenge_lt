package com.example.tallenge_lt;
//사용 xml settitlelist.xml 자바 Listset -class LsitSetAdapter 어댑터
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListSetActivity extends AppCompatActivity {

    private ListView ListSetView;
    private ListSetAdapter adapter2;
    private List<ListSet> listset;


    //체크리스트 분야설정 UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listset);

        ListSetView = (ListView) findViewById(R.id.listview2);
        listset=new ArrayList<ListSet>();
        listset.add(new ListSet("중국어"));
        listset.add(new ListSet("C언어"));
        listset.add(new ListSet("영어"));
        listset.add(new ListSet("일본어"));
        adapter2=new ListSetAdapter(getApplicationContext(),listset);
        ListSetView.setAdapter(adapter2);

        //뒤로가기버튼
        Button bck_btn = (Button) findViewById(R.id.bck_btn);
        bck_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChooseListActivity.class);
                startActivity(intent);
            }
        });
        // 리스트 새로만들기 버튼
        Button add_list = (Button) findViewById(R.id.add_list);
        add_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCheckList.class);
                startActivity(intent);
            }
        });
    }
}