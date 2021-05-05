package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseExpActivity extends AppCompatActivity {

    private ArrayList<ChooseExpData> arrayList;
    private ChooseExpAdapter chooseExpAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    int REQUEST_IMAGE_CODE = 1001;
    private Button btn_back;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_exp);

        recyclerView = (RecyclerView)findViewById(R.id.ch_exp_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        arrayList = new ArrayList<>();

        chooseExpAdapter = new ChooseExpAdapter(arrayList);
        recyclerView.setAdapter(chooseExpAdapter);

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