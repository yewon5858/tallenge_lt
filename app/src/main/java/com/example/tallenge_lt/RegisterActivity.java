package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private Spinner sp_address; //address 관련 드롭다운
    private TextView tv_result;//maddrass(주소) 대신 이용
    private Button mBtmRegister;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        // address Spinner 설정
        sp_address = (Spinner)findViewById( R.id.sp_address ); //address 스피너
        tv_result = (TextView)findViewById( R.id.tv_result ); // address 스피너 안의 값 보여주는 textview

        // 주소 스피너 아이템 클릭 시,
        sp_address.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //선택한 주소의 아이템을 TextView에 보여줌
                tv_result.setText(parent.getItemAtPosition( position ).toString());

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        //firebase 연결
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tallenge");

        // 이메일 아이디, 비밀번호, 닉네임 입력 및 회원가입 완료 버튼
        mEtEmail = findViewById( R.id.et_email );
        mEtPwd = findViewById( R.id.et_pwd );
        mnicname = findViewById( R.id.et_nicname );
        mBtmRegister = findViewById( R.id.btn_register );

        //회원가입 완료 버튼 클릭 시,
        mBtmRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strNic = mnicname.getText().toString(); // 닉네임 입력
                String strAdd =  tv_result.getText().toString(); // 주소 선택한 값 보여주기
//                //임시 입력 값 입니다<예원>
//                String mExp = ""; // 내 전문 분야
//                String mInterest = ""; // 내 관심 분야

                //Firebase Auth 진행(사용자 데이터 생성)
                mFirebaseAuth.createUserWithEmailAndPassword( strEmail, strPwd ).addOnCompleteListener( RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //현재 회원가입이 된 유저를 가져올 수 있게 된다.
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId( firebaseUser.getEmail()); //FirebaseUser의 getEmail()함수를 통해 이메일 값을 받아 UserAccount 파일에서 이메일 아이디를 넘겨준다.
                            account.setPassword( strPwd );
                            account.setNicname( strNic ); // 닉네임 set
                            account.setAddress( strAdd ); // 주소 set
                            //expdata child 생성
                            account.setExpdata( account.getExpdata());
                            account.setInterestdata( account.getInterestdata());

                            //setValue: database에 insert (삽입) 하는 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            // 회원가입에서 전문분야 선택으로 이동
                            Intent intent = new Intent(RegisterActivity.this, ExpActivity.class);
                            String IdToken = firebaseUser.getUid().toString();
                            intent.putExtra( "idToken",IdToken);
                            startActivity( intent );


                            Toast.makeText( RegisterActivity.this, "회원가입 정보가 정상적으로 저장되었습니다.",Toast.LENGTH_SHORT).show();

                        }  else {
                            Toast.makeText( RegisterActivity.this, "회원가입에 정보를 다시 입력해주세요.",Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
            }


        } );
    }
}

