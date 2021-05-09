package com.example.tallenge_lt;
//채팅 남은과제: 채팅방 아이디, 보낸시간 구현, 프로필리스트에서 닉네임intent값 받도록 바꾸기
// (현재 로그인액티비티에서 받아서 로그인 액티비티로 바로 넘어가도록 변경)
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
//ChatAdapter.java ,ChatData.java, chatview.xml
public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    //private String nick="nick1";
    private EditText inputText;
    private Button push_btn;
    String email;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_chat);
        //입력 edittext 출력 버튼 정의
        push_btn=findViewById(R.id.push_btn);
        inputText=findViewById(R.id.inputText);
        email=getIntent().getStringExtra("email");
//        <-->//에러나면 위에 각주되어있는 String nick="nick1"쓰시고 email변수 들어간 곳 다 nick으로 바꾸시고 테스트하시면됩니다.
        //버튼 클릭
        push_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Calendar c=Calendar.getInstance();
//                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM--DD hh:mm:ss");
//                String datetime =dateFormat.format(c.getTime());
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                myRef = database.getReference("tallenge").child("message").child(datetime);

                        String msg= inputText.getText().toString(); //메세지 값 받기
                        if(msg!=null) {
                            ChatData chat = new ChatData();
                            chat.setNickname(email);
                            chat.setMsg(msg);
                            myRef.push().setValue(chat);
                        }
            }
        });
        //뒤로가기버튼
        Button backButton = (Button) findViewById(R.id.btnbk);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        //재능교환하기/설정이동버튼
        Button goSet = (Button) findViewById(R.id.setbtn);
        goSet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChooseListActivity.class);
                startActivity(intent);
            }
        });
        //리싸이클러뷰 선언
        mRecyclerView=findViewById(R.id.rvmessage);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList=new ArrayList<>();
        //이메일로 되어있음->나중에 닉네임으로
        mAdapter=new ChatAdapter(chatList,ChatActivity.this,email);

        mRecyclerView.setAdapter(mAdapter);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tallenge").child("message");


//        ChatData chat= new ChatData();
//        chat.setNickname(nick);
//        chat.setMsg("hi");
//        myRef.setValue("Hello, World!");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ChatData chat= dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}







