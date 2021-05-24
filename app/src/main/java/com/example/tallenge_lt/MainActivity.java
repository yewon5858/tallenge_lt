package com.example.tallenge_lt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import main_fragment.expFragment;
import main_fragment.hobFragment;
import main_fragment.lanFragment;
import main_fragment.spoFragment;

public class MainActivity extends AppCompatActivity {
    // 하단 버튼 클릭으로 엑티비티 이동
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;
    String emailId; // 로그인한 사용자의 emailId
    String IdToken; // 로그인한 사용자의 uid
    String user; // 로그인한 사용자 정보
    private final String TAG = this.getClass().getSimpleName();
    Context mContext;
    private ViewPager2 mViewPager;
    private MyViewPagerAdapter myPagerAdapter;
    private TabLayout tabLayout;


    private String[] titles = new String[]{"전문분야", "취미", "언어", "스포츠"};


    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // fragment 이동
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        // intent를 통해서 회원가입할 때 emailId,uid 전달 받기
        emailId = getIntent().getStringExtra("emailId");
        IdToken = getIntent().getStringExtra("idToken");
        user = getIntent().getStringExtra("user");
        //Log.e("IdToken",IdToken);
        //Log.e("user",user);
        mContext = MainActivity.this;


        code = "";
        Log.e( TAG, code );

        Fragment frag1 = new expFragment().newInstance(code,IdToken);
        Fragment frag2 = new hobFragment().newInstance(code,IdToken);
        Fragment frag3 = new lanFragment().newInstance(code,IdToken);
        Fragment frag4 = new spoFragment().newInstance(code,IdToken);


        mViewPager = findViewById(R.id.viewPager2_container);
        tabLayout = findViewById(R.id.tabLayout);


        myPagerAdapter = new MyViewPagerAdapter(this);
        myPagerAdapter.addFrag(frag1);
        myPagerAdapter.addFrag(frag2);
        myPagerAdapter.addFrag(frag3);
        myPagerAdapter.addFrag(frag4);

        mViewPager.setAdapter(myPagerAdapter);

        //displaying tabs
        new TabLayoutMediator(tabLayout, mViewPager, (tab, position) -> tab.setText(titles[position])).attach();




        // MainActivity로 이동
        bt_home = (ImageButton) findViewById(R.id.home);
        bt_chat = (ImageButton) findViewById(R.id.chat);
        bt_alarm = (ImageButton) findViewById(R.id.alarm);
        bt_mypage = (ImageButton) findViewById(R.id.mypage);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                intent.putExtra( "idToken",IdToken );
                intent.putExtra( "emailId",emailId);
                intent.putExtra(  "user",user);
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
    // 뒤로가기 버튼을 눌러 이전 페이지로 이동을 막아줌
    @Override
    public void onBackPressed() {
        //super.onBackPressed(); // 이를 추가하게 되면 뒤로가기 버튼 사용이 가능해짐
    }
}
