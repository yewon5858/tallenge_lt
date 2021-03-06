package com.example.tallenge_lt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//체크리스트 세부항목추가 UI
public class SetCheckListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SetCheckListData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setchecklist);
        EditText editText=(EditText) findViewById(R.id.btn_chatitem);
        Intent intent =getIntent();
        //intent시 체크리스트 제목 값 가져오기
        String exp=intent.getStringExtra("checkTitle");
        editText.setText(exp);
        recyclerView =findViewById(R.id.rvsetcheck);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("tallenge").child("checklist").child(exp);
        //리싸이클러뷰에 데이터 출력
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
//                for(DataSnapshot snapshot:datasnapshot.getChildren()){
                //snapshot->datasnapshot
                    SetCheckListData setCheckListData=datasnapshot.getValue(SetCheckListData.class);
                    arrayList.add(setCheckListData);
//                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SetCheckListActivity",String.valueOf(error.toException()));


            }
        });
        adapter=new SetCheckListAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
        //리스트(전체) 삭제하기
        Button finish=findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCheckListData setCheckListData=new SetCheckListData();
                database.getReference("tallenge").child("checklist").child(exp).setValue(setCheckListData);
                Intent intent= new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
                }
        });

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
        Button move =(Button)findViewById(R.id.move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ListSetActivity.class);
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