package com.example.tallenge_lt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ChooseExpActivity extends AppCompatActivity {

    int REQUEST_IMAGE_CODE = 1001;
    private Button btn_back;
    private ImageButton btn_go_home;
    private ImageButton btn_go_chat_list;
    private ImageButton btn_go_alarm;
    private ImageButton btn_go_myinfo;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_exp);

        btn_back = findViewById(R.id.btn_move);   //화면 이동 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (ChooseExpActivity.this, MyInfoActivity.class);
                startActivity(intent);  //액티비티 이동
            }
        });
        btn_go_home = findViewById(R.id.bt_home);
        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseExpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_go_chat_list = findViewById(R.id.bt_chat);
        btn_go_chat_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseExpActivity.this, ChatListActivity.class);
                startActivity(intent);
            }
        });
        btn_go_alarm = findViewById(R.id.bt_alarm);
        btn_go_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseExpActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });
        btn_go_myinfo = findViewById(R.id.bt_myinfo);
        btn_go_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseExpActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });
        imageView = (ImageView) findViewById(R.id.ex_image);    //갤러리에서 사진 가져오기
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in, REQUEST_IMAGE_CODE);
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
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}