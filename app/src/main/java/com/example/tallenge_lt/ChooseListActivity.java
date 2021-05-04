package com.example.tallenge_lt;
//사용 xml exchangelist.xml / 자바 ExchangeList.java , ExchageAdapter.java (변수중복때문에 오타로했습니다!)
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChooseListActivity extends AppCompatActivity {
    //변수 선언
    private ListView exchangeListView;
    private ExchageAdapter adapter;
    private List<ExchangeList> exchangelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_list);
        //교환리스트 구현
        exchangeListView = (ListView) findViewById(R.id.listview1);
        exchangelist=new ArrayList<ExchangeList>();
        exchangelist.add(new ExchangeList("중국어"));
        exchangelist.add(new ExchangeList("C언어"));
        exchangelist.add(new ExchangeList("영어"));
        exchangelist.add(new ExchangeList("일본어"));
        adapter=new ExchageAdapter(getApplicationContext(),exchangelist);
        exchangeListView.setAdapter(adapter);




//뒤로가기버튼
        Button backchat = (Button) findViewById(R.id.btnbk2);
        backchat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
        //체크리스트 설정이동버튼
        Button goCheckList = (Button) findViewById(R.id.set);
        goCheckList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListSetActivity.class);
                startActivity(intent);
            }
        });
        //설정완료버튼
        Button finChoice = (Button) findViewById(R.id.finSet);
        finChoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListAndAlarmActivity.class);
                startActivity(intent);
            }
        });
//        //프로그램실행
//        new BackgoundTask().execute();
    }
    //php 서버 불러오기 class
    class BackgoundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected  void onPreExecute(){
            target="http://203.255.3.92:9000/phpmyadmin/ChatList.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url=new URL(target);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while((temp =bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }
        //체크리스트 출력
        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("response");
                int count=0;
                String checklist_title;
                while(count<jsonArray.length()){
                    JSONObject object=jsonArray.getJSONObject(count);
                    checklist_title=object.getString("checklist_title");
                    ExchangeList exchangeList=new ExchangeList(checklist_title);
                    exchangelist.add(exchangeList);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}