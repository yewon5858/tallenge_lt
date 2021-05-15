package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChooseExpActivity extends AppCompatActivity {

    private ArrayList<ChooseExpData> arrayList;
    private ChooseExpAdapter chooseExpAdapter;
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private LinearLayoutManager linearLayoutManager;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    int REQUEST_IMAGE_CODE = 1001;
    private Button btn_back;
    private Button btn_go_home;
    private Button btn_go_chat_list;
    private Button btn_go_alarm;
    private Button btn_go_myinfo;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_exp);


        recyclerView = (RecyclerView)findViewById(R.id.ch_exp_rv);
        //recyclerView.setHasFixedSize(true);  //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();  //객체 담을 어레이 리스트

        chooseExpAdapter = new ChooseExpAdapter(arrayList);
        recyclerView.setAdapter(chooseExpAdapter);

        //database = FirebaseDatabase.getInstance();  //파이어베이스 데이터베이스 연동
        //databaseReference = database.getReference("tallenge").child("certifyexp");   //DB테이블 연결
        //파이어베이스
        /*
        FirebaseDatabase.getInstance().getReference()
                .child("tallenge")
                .child("certifyexp")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        AlarmData alarmData1= dataSnapshot.getValue(AlarmData.class);


                        /*
                        if (consumer == null)
                        {
                            //data does not exists for the specified key
                        }
                        else if(data.checked)
                        {
                            //data is checked
                        }
                        else {
                            //data not checked
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
        adapter = new ChooseExpAdapter(arrayList,this);   //this가 context 의미
        recyclerView.setAdapter(adapter);  //리사이클러뷰 어뎁터 연결

*/



        Button btn_add = (Button)findViewById(R.id.btn_ch_exp_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseExpData chooseExpData = new ChooseExpData(R.drawable.certificated_icon,"언어");
                arrayList.add(chooseExpData);
                chooseExpAdapter.notifyDataSetChanged();  //새로고침
            }
        });

        btn_back = findViewById(R.id.btn_move);   //화면 이동 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (ChooseExpActivity.this, MyInfoActivity.class);
                startActivity(intent);  //액티비티 이동
            }
        });
        //
        // imageView = (ImageView) findViewById(R.id.ex_image);    //갤러리에서 사진 가져오기
       // imageView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(in, REQUEST_IMAGE_CODE);
            //}
        //});
       // }

   // @Override
    //public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //if(requestCode == REQUEST_IMAGE_CODE){
           // Uri image = data.getData();
            //try {
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
                ///imageView.setImageBitmap(bitmap);
           // } catch (IOException e) {
                //e.printStackTrace();
           // }
       // }
    }
}