package com.example.tallenge_lt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class CertifyExpActivity extends AppCompatActivity {
    private ArrayList<CertifyExpData> arrayList;
    private CertifyExpAdapter certifyExpAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    int REQUEST_IMAGE_CODE = 1001;
    ImageView image1;
    private Button btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifyexp);



        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        arrayList = new ArrayList<>();

        certifyExpAdapter = new CertifyExpAdapter(arrayList);
        recyclerView.setAdapter(certifyExpAdapter);

        btn_back = findViewById(R.id.btn_back);   //화면 이동 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (CertifyExpActivity.this, ChooseExpActivity.class);
                startActivity(intent);  //액티비티 이동
            }
        });



        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in, REQUEST_IMAGE_CODE);

                //CertifyExpData certifyExpData = new CertifyExpData(R.mipmap.ic_launcher,"카테고리","세부 카테고리");
                //arrayList.add(certifyExpData);
                //certifyExpAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CODE){
            Uri image = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
                //image1.setImageBitmap(bitmap);
                CertifyExpData certifyExpData = new CertifyExpData(bitmap,"카테고리","세부 카테고리");
                arrayList.add(certifyExpData);
                certifyExpAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}