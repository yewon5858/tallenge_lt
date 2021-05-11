package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

public class ChooseExpActivity extends AppCompatActivity {

    private ArrayList<ChooseExpData> arrayList;
    //private ChooseExpAdapter chooseExpAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private LinearLayoutManager linearLayoutManager;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    int REQUEST_IMAGE_CODE = 1001;
    private Button btn_back;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_exp);

        recyclerView = (RecyclerView)findViewById(R.id.ch_exp_rv);
        recyclerView.setHasFixedSize(true);  //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();  //객체 담을 어레이 리스트


        database = FirebaseDatabase.getInstance();  //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("tallenge");   //DB테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();  //기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {   //반복문으로 데이터 리스트 추출
                    ChooseExpData chooseExpData = snapshot1.getValue(ChooseExpData.class);// 만들어뒀던 객체에 데이터를 담는다.
                    arrayList.add(chooseExpData);  //담은 데이터를 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();   //리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비를 가져오던중 에러 발생시
                Log.e("ChooseExpActivity", String.valueOf(error.toException()));  //에러문 출력

            }
        });

        adapter = new ChooseExpAdapter(arrayList,this);   //this가 context 의미
        recyclerView.setAdapter(adapter);  //리사이클러뷰 어뎁터 연결




        Button btn_add = (Button)findViewById(R.id.btn_ch_exp_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        //imageView = (ImageView) findViewById(R.id.ex_image);    //갤러리에서 사진 가져오기
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