package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 파이어베이스 데이터 경로
    private EditText mEtEmail, mEtPwd; // 이메일 아이디 및 비밀번호


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance(); // 파이어베이스 인증
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tallenge"); // firebase 의 "tallenge" (경로)

        // 이메일아이디, 비밀번호 연결
        mEtEmail = findViewById( R.id.et_email );
        mEtPwd = findViewById( R.id.et_pwd );

        //로그인 버튼
        Button btn_login = findViewById( R.id.btn_login );
        //로그인 버튼 클릭 시,
        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();


                //Firebase Auth 진행(사용자 데이터 확인 및 로그인)
                // email 또는 pwd 입력이 없는 경우에 대한 예외 처리
                if (strEmail.equals( "" ) || strPwd.equals( "" )) {
                    String email = "",pwd = "";
                    if(strEmail.equals( "" )){ email = "이메일 ";};
                    if(strPwd.equals( "" )){ pwd = "비밀번호 ";};
                    Toast.makeText( LoginActivity.this, email + pwd +"입력이 존재하지 않습니다. 다시 입력해주세요", Toast.LENGTH_SHORT ).show();
                }

                else { // 정상적인 입력인 경우 로그인 기능 실행
                    mFirebaseAuth.signInWithEmailAndPassword( strEmail, strPwd ).addOnCompleteListener( LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // 존재하는 사용자인 경우,
                            if (task.isSuccessful()) {
                                Intent intent = new Intent( LoginActivity.this, MainActivity.class );//프로필에서 이메일이나 닉네임을 못받아서 임시로 바꿨습니다!
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //로그인된 사용자 정보를 받음
                                // Log.e("user_value",user+"입니다."); //값 확인
                                String struser = user != null ? user.toString() : null; //user의 사용자정보 값 받기
                                // Log.e("uid_value",uid+"입니다."); //값 확인
                                String uid = user != null ? user.getUid() : null; //user의 uid 값 받기
                                //임시로 채팅에 이메일값 받아오도록 설정했습니다<언승>
                                intent.putExtra( "emailId", strEmail );
                                intent.putExtra( "idToken", uid );
                                intent.putExtra( "user", struser );
                                startActivity( intent );
                                finish(); // 현재 액티비티 파괴
                                Toast.makeText( LoginActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT ).show();
                            } else {
                                //로그인 실패
                                Toast.makeText( LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }
        } );
        // 회원가입 화면으로 이동하기
        Button btn_register = findViewById( R.id.btn_register ); // 회원가입 버튼
        //회원가입 버튼 클릭 시,
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity( intent );
            }
        } );

        };
    }






