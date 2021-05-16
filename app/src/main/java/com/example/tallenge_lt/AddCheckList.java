package com.example.tallenge_lt;
//액티비티입니다!
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//새로운 체크리스트 추가 UI
public class AddCheckList extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    Button complt;
    EditText title, edit1, edit2, edit3, edit4, edit5;


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

        complt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCheck(title.getText().toString(), edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString());
                Toast.makeText(AddCheckList.this, "저장되었습니다!", Toast.LENGTH_LONG).show();
            }
        });

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


    }

    public void AddCheck(String CheckTitle,String checkitem1,String checkitem2,String checkitem3,String checkitem4,String checkitem5){
        if(CheckTitle!=null &&checkitem1!=null&&checkitem2!=null&&checkitem3!=null&&checkitem4!=null&&checkitem5!=null) {
            AddCheckData checkData = new AddCheckData(CheckTitle, checkitem1, checkitem2, checkitem3, checkitem4, checkitem5);
            databaseReference.child("tallenge").child("checklist").child(CheckTitle).setValue(checkData);
        }
    }
}

