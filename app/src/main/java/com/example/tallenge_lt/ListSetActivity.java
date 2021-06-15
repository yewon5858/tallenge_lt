package com.example.tallenge_lt;
//사용 xml settitlelist.xml 자바 Listset -class ListSetAdapter 어댑터
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ListSet> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;



    //    private ArrayList<Set> set=new ArrayList<Set>();
    //체크리스트 분야설정 UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listset);
        recyclerView=findViewById(R.id.listview2);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference("tallenge").child("checklist");
        //DB내용 출력 및 변경 저장
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot:datasnapshot.getChildren()){
                    ListSet listSet=snapshot.getValue(ListSet.class);
                    arrayList.add(listSet);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ListSetActivity", String.valueOf(error.toException()));

            }
        });
        adapter=new ListSetAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);


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
        // MainActivity로 이동
        bt_home = (ImageButton) findViewById(R.id.home);
        bt_chat = (ImageButton) findViewById(R.id.chat);
        bt_alarm = (ImageButton) findViewById(R.id.alarm);
        bt_mypage = (ImageButton) findViewById(R.id.mypage);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        });
        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ChatActivity.class );
                startActivity( intent );
            }
        });
        bt_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), SetAlarmActivity.class );
                startActivity( intent );
            }
        });
        bt_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MyInfoActivity.class );
                startActivity( intent );
            }
        });
    }
}