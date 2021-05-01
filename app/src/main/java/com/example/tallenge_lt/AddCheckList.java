package com.example.tallenge_lt;

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

import java.util.ArrayList;

//새로운 체크리스트 추가 UI
public class AddCheckList extends AppCompatActivity {
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ListView listView;
    Button btnAdd;
    Button btnDel;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchecklist);
        Items= new ArrayList<String>();


        Adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,Items)
        {
            @Override
            //글자 색 변경
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view=super.getView(position,convertView,parent);
                TextView tv= (TextView)view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                return view;
            }
        };

        listView=(ListView) findViewById(R.id.listview3);
        listView.setAdapter(Adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        editText=(EditText) findViewById(R.id.btn_check);
        btnAdd=(Button)findViewById(R.id.add_btn);
        btnDel=(Button)findViewById(R.id.remove);

        btnAdd.setOnClickListener(listener);
        btnDel.setOnClickListener(listener);

        //뒤로가기 버튼
        Button btn_back2 = (Button) findViewById(R.id.bck_btn2);
        btn_back2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
        // 작성 완료 버튼
        Button btn_complt = (Button) findViewById(R.id.complt);
        btn_complt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
    }
    private  View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_btn:
                    String text =editText.getText().toString();
                    if (text.length()!=0){
                        Items.add(text);
                        editText.setText("");
                        Adapter.notifyDataSetChanged();
                    }
                    break;
                case R.id.remove:
                    int pos;
                    pos=listView.getCheckedItemPosition();
                    if(pos!=ListView.INVALID_POSITION){
                        Items.remove(pos);
                        listView.clearChoices();
                        Adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

}