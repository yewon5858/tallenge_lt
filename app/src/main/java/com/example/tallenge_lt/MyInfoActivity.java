package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyInfoActivity extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;
    Button btn_back, btn_exp, btn_checklist, btn_interest, btn_modifyinfo;
    TextView textView;

    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();;

    DatabaseReference databaseRef;

    String emailId;
    String IdToken;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_exp = (Button) findViewById(R.id.btn_go_exp);
        btn_checklist = (Button)findViewById(R.id.btn_go_checklist);
        btn_interest = (Button)findViewById(R.id.btn_go_itr);
        btn_modifyinfo = (Button)findViewById(R.id.btn_go_modifyinfo);


        emailId = getIntent().getStringExtra("emailId");
        IdToken = getIntent().getStringExtra("idToken");
        user = getIntent().getStringExtra("user");



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, MainActivity.class) ;
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ChooseExpActivity.class) ;
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        btn_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, SetCheckListActivity.class) ;
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        btn_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, InterestActivity.class) ;
                startActivity( intent );
            }
        });
        btn_modifyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ModifyInfoActivity.class) ;
                intent.putExtra( "idToken",IdToken );
                intent.putExtra( "emailId",emailId);
                intent.putExtra(  "user",user);
                startActivity( intent );
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
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ChatActivity.class );
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        bt_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), SetAlarmActivity.class );
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });
        bt_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MyInfoActivity.class );
                emailId = getIntent().getStringExtra("emailId");
                IdToken = getIntent().getStringExtra("idToken");
                user = getIntent().getStringExtra("user");
                startActivity( intent );
            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef= firebaseDatabase.getReference("tallenge").child("UserAccount").child(user.getUid());
        textView = (TextView)findViewById(R.id.id);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String data = dataSnapshot.child("emailId").getValue( String.class );
                textView.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}