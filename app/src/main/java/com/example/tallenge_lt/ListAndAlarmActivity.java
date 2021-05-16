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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListAndAlarmActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private RecyclerView.Adapter adapter;
   private RecyclerView.LayoutManager layoutManager;
   private ArrayList<ListAlarmData> arrayList;
   private FirebaseDatabase database;
   private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listandalarm);
        Intent intent=getIntent();
        String title=intent.getStringExtra("Title");
        recyclerView=findViewById(R.id.rvlistalarm);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("tallenge").child("checklist").child(title);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                ListAlarmData listAlarmData=datasnapshot.getValue(ListAlarmData.class);
                arrayList.add(listAlarmData);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ListAndAlarmActivity",String.valueOf(error.toException()));

            }
        });
        adapter=new ListAlarmAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);



//뒤로가기
        Button back_btn3 = (Button) findViewById(R.id.btnbk3);
        back_btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChooseListActivity.class);
                startActivity(intent);
            }
        });
//재능교환완료
        Button Finish_btn = (Button) findViewById(R.id.Fin);
        Finish_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //알림설정 버튼
        Button imageButton = (Button) findViewById(R.id.goAlarm);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SetAlarmActivity.class);
                startActivity(intent);
            }
        });

    }

}