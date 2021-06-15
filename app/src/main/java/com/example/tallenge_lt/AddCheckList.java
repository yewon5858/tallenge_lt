package com.example.tallenge_lt;
//액티비티입니다!
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//새로운 체크리스트 추가 UI
public class AddCheckList extends AppCompatActivity {
    //변수선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    Button complt;
    EditText title, edit1, edit2, edit3, edit4, edit5;
    ImageButton bt_home;
    ImageButton bt_chat;
    ImageButton  bt_alarm;
    ImageButton bt_mypage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchecklist);

        complt = findViewById(R.id.complt);
        title=findViewById(R.id.title);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        edit5 = findViewById(R.id.edit5);
        //저장버튼 클릭
        complt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCheck(title.getText().toString(), edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString());
                Toast.makeText(AddCheckList.this, "저장되었습니다!", Toast.LENGTH_LONG).show();
            }
        });
        //설정완료 버튼 클릭
        Button end=(Button) findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
        //뒤로가기 버튼
        Button btn_back2 = (Button) findViewById(R.id.bck_btn2);
        btn_back2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListSetActivity.class);
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
    //생성 체크리스트를 DB에 저장
    public void AddCheck(String CheckTitle,String checkitem1,String checkitem2,String checkitem3,String checkitem4,String checkitem5){
        if(CheckTitle.length()!=0 &&(checkitem1.length()!=0||checkitem2.length()!=0||checkitem3.length()!=0||checkitem4.length()!=0||checkitem5.length()!=0)) {
            AddCheckData checkData = new AddCheckData(CheckTitle, checkitem1, checkitem2, checkitem3, checkitem4, checkitem5);
            databaseReference.child("tallenge").child("checklist").child(CheckTitle).setValue(checkData);
        }
    }
}
