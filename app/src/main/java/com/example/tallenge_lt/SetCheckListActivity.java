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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setchecklist);
        EditText editText=(EditText) findViewById(R.id.btn_chatitem);
        Intent intent =getIntent();
        String exp=intent.getStringExtra("checkTitle");
        editText.setText(exp);
        recyclerView =findViewById(R.id.rvsetcheck);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("tallenge").child("checklist").child(exp);
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