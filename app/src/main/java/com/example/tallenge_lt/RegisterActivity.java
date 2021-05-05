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


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef;
    private EditText mEtEmail, mEtPwd;
    private EditText mnicname;
    private String maddrass;// -> 닉네임, 주소
    private Button mBtmlogin,mBtmRegister; // 로그인 버튼은 intent로 구현


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tallenge");

        mEtEmail = findViewById( R.id.et_email );
        mEtPwd = findViewById( R.id.et_pwd );
        mBtmRegister = findViewById( R.id.btn_register );
        mBtmlogin = findViewById( R.id.btn_login );



        mBtmlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 화면으로 이동
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity( intent );
            }
        } );

        mBtmRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();


                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword( strEmail, strPwd ).addOnCompleteListener( RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //현재 회원가입이 된 유저를 가져올 수 있게 된다.
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId( firebaseUser.getEmail()); //FirebaseUser의 getEmail()함수를 통해 이메일 값을 받아 UserAccount 파일에서 이메일 아이디를 넘겨준다.
                            account.setPassword( strPwd );

                            //setValue: database에 insert (삽입) 하는 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText( RegisterActivity.this, "회원가입에 성공하였습니다.",Toast.LENGTH_SHORT).show();

                        }  else {
                            Toast.makeText( RegisterActivity.this, "회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
            }
        } );
    }
}

