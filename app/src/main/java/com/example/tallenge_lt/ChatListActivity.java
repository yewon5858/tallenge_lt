package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;
    private String nickname= "nick1";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ChatData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //미구현으로 recyclerview 변수 사용 x
        setContentView(R.layout.activity_chatlist);
        recyclerView=findViewById(R.id.rvchatlist);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("tallenge").child("chatlist");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot:datasnapshot.getChildren()){
                    ChatListData chatListData=snapshot.getValue(ChatListData.class);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter=new ChatAdapter(arrayList,this,nickname);
        recyclerView.setAdapter(adapter);

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
        //뒤로가기 버튼
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //구현 못했을시 발표자료...
//        Button gochat = (Button) findViewById(R.id.goChat);
//        gochat.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gochat2 = (Button) findViewById(R.id.goChat2);
//        gochat2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gochat3 = (Button) findViewById(R.id.goChat3);
//        gochat3.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
//                startActivity(intent);
//            }
//        });

    }

}