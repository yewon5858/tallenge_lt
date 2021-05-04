package com.example.tallenge_lt;
//사용 xml settitlelist.xml 자바 Listset -class ListSetAdapter 어댑터
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    private ArrayList<Set> set=new ArrayList<Set>();

    //체크리스트 분야설정 UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listset);

        set.add(new Set("중국어"));
        set.add(new Set("C언어"));
        set.add(new Set("영어"));
        set.add(new Set("일본어"));

        ListSetAdapter adapter=new ListSetAdapter(getApplicationContext(),R.layout.settitlelist,set);
        ListView listView=(ListView)findViewById(R.id.listview2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),SetCheckListActivity.class);
                intent.putExtra("title",set.get(position).title);
                startActivity(intent);
            }
        });

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