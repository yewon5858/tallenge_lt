package com.example.tallenge_lt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallenge_lt.Exp_catagory.Exp_item;
import com.example.tallenge_lt.Exp_catagory.Hobby_item;
import com.example.tallenge_lt.Exp_catagory.Lan_item;
import com.example.tallenge_lt.Exp_catagory.Spo_item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExpActivity extends AppCompatActivity {
    //데이터베이스 연결
    private DatabaseReference mDatabaseRef;
    // 전문분야 string 선언을 위한 내용
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem_exp; // 전문분야 카테고리 - 전문분야
    private ListView exp_menu; // 전문분야 카테고리 - 전문분야 리스트 뷰

    ArrayList<String> listItem_hobby;// 전문분야 카테고리 - 취미
    private ListView hobby_menu; // 전문분야 카테고리 - 취미 리스트 뷰

    ArrayList<String> listItem_lan; // 전문분야 카테고리 - 언어
    private ListView lan_menu; // 전문분야 카테고리 - 언어 리스트 뷰

    ArrayList<String> listItem_spo; // 전문분야 카테고리 - 스포츠
    private ListView spo_menu; // 전문분야 카테고리 - 스포츠 리스트 뷰

    String IdToken; // 로그인한 사용자의 IdToken
    Button bt_set; // 완료 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);

        //firebase 연결
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tallenge");
        // 완료버튼
        bt_set = (Button)findViewById( R.id.bt_set );

        //데이터 입력 (전문 분야)
        listItem_exp = new ArrayList<String>();
        listItem_exp.add("컴퓨터");
        listItem_exp.add("주식");
        listItem_exp.add("역사");
        listItem_exp.add("수학");

        //데이터 입력 (취미)
        listItem_hobby = new ArrayList<String>();
        listItem_hobby.add("음악");
        listItem_hobby.add("타로");
        listItem_hobby.add("레진공예");
        listItem_hobby.add("그림");

        //데이터 입력 (언어)
        listItem_lan = new ArrayList<String>();
        listItem_lan.add("영어");
        listItem_lan.add("중국어");
        listItem_lan.add("일본어");
        listItem_lan.add("스페인어");

        //데이터 입력 (스포츠)
        listItem_spo = new ArrayList<String>();
        listItem_spo.add("수영");
        listItem_spo.add("필라테스");
        listItem_spo.add("홈트");
        listItem_spo.add("배드민턴");

        // 리스트 뷰를 체크리스트로 표현
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem_exp);
        exp_menu = findViewById(R.id.exp_menu);
        exp_menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        exp_menu.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem_hobby);
        hobby_menu = findViewById(R.id.hobby_menu);
        hobby_menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        hobby_menu.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem_lan);
        lan_menu = findViewById(R.id.lan_menu);
        lan_menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lan_menu.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem_spo);
        spo_menu = findViewById(R.id.spo_menu);
        spo_menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        spo_menu.setAdapter(adapter);


        // intent를 통해서 회원가입할 때 IdToken 전달 받기
        IdToken = getIntent().getStringExtra("idToken");
        Expdata expdata = new Expdata();

        Exp_item exp_item = new Exp_item();
        Hobby_item hobby_item = new Hobby_item();
        Lan_item lan_item = new Lan_item();
        Spo_item spo_item = new Spo_item();


        ArrayList<ListView> View_Menu = new ArrayList<ListView>();
        View_Menu.add(exp_menu);
        View_Menu.add(hobby_menu);
        View_Menu.add(lan_menu);
        View_Menu.add(spo_menu);

        for (int j = 0 ; j < 4 ; j++){

            View_Menu.get(j).setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }

//        //exp_menu의 리스트를 선택했을 때,
//        exp_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                // ischecked()를 통해서 선택이 되었는지 확인한다.
//                CheckedTextView selected = (CheckedTextView) view;
//                String bool = "";
//
//                if (selected.isChecked()) bool = "true";
//                else bool = "false";
//
//                String s = "isChecked : " + bool + ", text : " + selected.getText();
//
//                //선택이 되었다고 알림
//                Toast.makeText(getApplicationContext(), s,Toast.LENGTH_SHORT).show();
//                // Toast.makeText(getApplicationContext(),listItem.get(i).toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        });


        // 전문분야를 모두 선택한 후 완료버튼을 눌렀을 때
        bt_set.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전문분야 속성 값 입력을 위한 문장 : ture / false로 데이터 저장 -> 원래는 int 값으로 설정할 예정이였음(변경사항)
                for(int j = 0 ; j < 4 ; j++) {
                    for (int c = 0; c < 4; c++) {

                        // 배열 리스트의 값을 받는 문자열 선언
                        ArrayList<ArrayList> List_Menu = new ArrayList<ArrayList>();
                        List_Menu.add( listItem_exp );
                        List_Menu.add( listItem_hobby );
                        List_Menu.add( listItem_lan );
                        List_Menu.add( listItem_spo );

                        String checked_list = (List_Menu.get( j )).get( c ).toString();

                        //전문분야 카테고리의 전문분야 속성 입력
                        switch (checked_list) {
                            // 전문분야
                            case "컴퓨터":
                                exp_item.setComputer(View_Menu.get(j).isItemChecked( c ) + "");
                                break;

                            case "주식":
                                exp_item.setStock( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "역사":
                                exp_item.setHistory( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "수학":
                                exp_item.setMath( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            // 취미
                            case "음악":
                                hobby_item.setMusic( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "타로":
                                hobby_item.setTarot( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "레진공예":
                                hobby_item.setResin( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "그림":
                                hobby_item.setDrawing( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                                // 언어
                            case "영어":
                                lan_item.setEng( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "중국어":
                                lan_item.setChi( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "일본어":
                                lan_item.setJap( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "스페인어":
                                lan_item.setSpan( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                                // 스포츠
                            case "수영":
                                spo_item.setSwim( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "필라테스":
                                spo_item.setPilates( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "홈트":
                                spo_item.setHT( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;

                            case "배드민턴":
                                spo_item.setBadminton( View_Menu.get( j ).isItemChecked( c ) + "" );
                                break;
                        }
                    }
                }
                // expdata child 안에 세부 item child 4개를 생성한다. (4개를 생성했으므로 데이터베이스 업데이트 때 4개의 child의 값을 업데이트 함)
                expdata.setExp_item(expdata.getExp_item());
                expdata.setHobby_item(expdata.getHobby_item());
                expdata.setLan_item(expdata.getLan_item());
                expdata.setSpo_item(expdata.getSpo_item());


                //데이터베이스 업데이트(전문분야 카테고리의 세부 아이템 내용만 변경된다. )
                mDatabaseRef.child("UserAccount").child(IdToken).child("expdata").child("Exp_item").setValue(exp_item);
                mDatabaseRef.child("UserAccount").child(IdToken).child("expdata").child("Hobby_item").setValue(hobby_item);
                mDatabaseRef.child("UserAccount").child(IdToken).child("expdata").child("Lan_item").setValue(lan_item);
                mDatabaseRef.child("UserAccount").child(IdToken).child("expdata").child("Spo_item").setValue(spo_item);

                //다음 액티비티로의 이동
                Intent intent = new Intent(ExpActivity.this,InterestActivity.class);
                intent.putExtra( "idToken", IdToken);
                startActivity( intent );
            }
        } );


    }

    // 뒤로가기 버튼을 눌러 이전 페이지로 이동을 막아줌
    @Override
    public void onBackPressed() {
       //super.onBackPressed(); // 이를 추가하게 되면 뒤로가기 버튼 사용이 가능해짐
    }

}

