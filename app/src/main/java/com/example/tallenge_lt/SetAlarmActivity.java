//List and alarm에 들어가야할 코드
//현재 문제점: 다중 선택시 알람이 제대로 안감, 교환하는 db만들고 나서 db 이동하기

package com.example.tallenge_lt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SetAlarmActivity extends AppCompatActivity {

    ImageButton bt_home,bt_chat,bt_alarm,bt_mypage;
    AlarmData alarmData = new AlarmData();
    CheckBox sun,mon,tue,wed,thu,fri,sat;
//    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

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


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tallenge").child("alarm");
        // 앞서 설정한 값으로 보여주기
        // 없으면 디폴트 값은 현재시간
        SharedPreferences sharedPreferences = getSharedPreferences("daily alarm", MODE_PRIVATE);
        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());

        Calendar nextNotifyTime = new GregorianCalendar();
        nextNotifyTime.setTimeInMillis(millis);

        //Date nextDate = nextNotifyTime.getTime();
        //String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(nextDate);
        //Toast.makeText(getApplicationContext(),"[처음 실행시] 다음 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();


        // 이전 설정값으로 TimePicker 초기화
       // Date currentTime = nextNotifyTime.getTime();
        //SimpleDateFormat HourFormat = new SimpleDateFormat("kk", Locale.getDefault());
       // SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm", Locale.getDefault());

        //int pre_hour = Integer.parseInt(HourFormat.format(currentTime));
        //int pre_minute = Integer.parseInt(MinuteFormat.format(currentTime));



        sun = findViewById(R.id.cb_sun);
        mon = findViewById(R.id.cb_mon);
        tue = findViewById(R.id.cb_tue);
        wed = findViewById(R.id.cb_wed);
        thu = findViewById(R.id.cb_thu);
        sat = findViewById(R.id.cb_sat);
        fri = findViewById(R.id.cb_fri);



//시간 설정
        int hour = 1;
        int minute = 44;

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);

        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        Button button = (Button) findViewById(R.id.btn_set_alarm1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //alarmData에 요일별로 부울로 넣음(파이어베이스에도)
                alarmData.setSun(sun.isChecked());
                myRef.setValue(alarmData);
                alarmData.setMon(mon.isChecked());
                myRef.setValue(alarmData);
                alarmData.setTue(tue.isChecked());
                myRef.setValue(alarmData);
                alarmData.setWed(wed.isChecked());
                myRef.setValue(alarmData);
                alarmData.setThu(thu.isChecked());
                myRef.setValue(alarmData);
                alarmData.setFri(fri.isChecked());
                myRef.setValue(alarmData);
                alarmData.setSat(sat.isChecked());
                myRef.setValue(alarmData);


                //요일별로 체크되어있으면 푸시 알람
                if(sun.isChecked()){

                    Calendar calendarSun = Calendar.getInstance();
                    calendarSun.setTimeInMillis(System.currentTimeMillis());
                    calendarSun.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                    calendarSun.set(Calendar.HOUR_OF_DAY, hour);
                    calendarSun.set(Calendar.MINUTE, minute);
                    calendarSun.set(Calendar.SECOND, 0);

                    if (calendarSun.before(Calendar.getInstance())) {
                        calendarSun.add(Calendar.DATE, 7);
                    }

                    //Date currentDateTime = calendarSun.getTime();
                    //String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
                    //Toast.makeText(getApplicationContext(), date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();


                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarSun.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentSun = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarSun,pendingIntentSun);

                }
                if(mon.isChecked()){
                    Calendar calendarMon = Calendar.getInstance();
                    calendarMon.setTimeInMillis(System.currentTimeMillis());
                    calendarMon.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
                    calendarMon.set(Calendar.HOUR_OF_DAY, hour);
                    calendarMon.set(Calendar.MINUTE, minute);
                    calendarMon.set(Calendar.SECOND, 0);

                    if (calendarMon.before(Calendar.getInstance())) {
                        calendarMon.add(Calendar.DATE, 7);
                    }
                    //Date currentDateTime = calendarMon.getTime();
                    //String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
                    //Toast.makeText(getApplicationContext(), date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarMon.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentMon = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarMon,pendingIntentMon);

                }
                if(tue.isChecked()){
                    Calendar calendarTue = Calendar.getInstance();
                    calendarTue.setTimeInMillis(System.currentTimeMillis());
                    calendarTue.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
                    calendarTue.set(Calendar.HOUR_OF_DAY, hour);
                    calendarTue.set(Calendar.MINUTE, minute);
                    calendarTue.set(Calendar.SECOND, 0);

                    if (calendarTue.before(Calendar.getInstance())) {
                        calendarTue.add(Calendar.DATE, 7);
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarTue.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentTue = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarTue,pendingIntentTue);

                }
                if(wed.isChecked()){
                    Calendar calendarWed = Calendar.getInstance();
                    calendarWed.setTimeInMillis(System.currentTimeMillis());
                    calendarWed.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
                    calendarWed.set(Calendar.HOUR_OF_DAY, hour);
                    calendarWed.set(Calendar.MINUTE, minute);
                    calendarWed.set(Calendar.SECOND, 0);

                    if (calendarWed.before(Calendar.getInstance())) {
                        calendarWed.add(Calendar.DATE, 7);
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarWed.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentWed = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarWed,pendingIntentWed);

                }
                if(thu.isChecked()) {
                    // 현재 지정된 시간으로 알람 시간 설정
                    Calendar calendarThu = Calendar.getInstance();
                    calendarThu.setTimeInMillis(System.currentTimeMillis());
                    calendarThu.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
                    calendarThu.set(Calendar.HOUR_OF_DAY, hour);
                    calendarThu.set(Calendar.MINUTE, minute);
                    calendarThu.set(Calendar.SECOND, 0);


                    // 이미 지난 시간을 지정했다면 7일 후 같은 시간으로 설정
                    if (calendarThu.before(Calendar.getInstance())) {
                        calendarThu.add(Calendar.DATE, 7);
                    }

                    //Date currentDateTime = calendar.getTime();
                    //String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
                    //Toast.makeText(getApplicationContext(), date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();

                    //  Preference에 설정한 값 저장
                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarThu.getTimeInMillis());
                    editor.apply();

                    PendingIntent pendingIntentThu = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarThu,pendingIntentThu);
                }
                if(fri.isChecked()){
                    Calendar calendarFri = Calendar.getInstance();
                    calendarFri.setTimeInMillis(System.currentTimeMillis());
                    calendarFri.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
                    calendarFri.set(Calendar.HOUR_OF_DAY, hour);
                    calendarFri.set(Calendar.MINUTE, minute);
                    calendarFri.set(Calendar.SECOND, 0);

                    if (calendarFri.before(Calendar.getInstance())) {
                        calendarFri.add(Calendar.DATE, 7);
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarFri.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentFri = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarFri,pendingIntentFri);

                }
                if(sat.isChecked()){
                    Calendar calendarSat = Calendar.getInstance();
                    calendarSat.setTimeInMillis(System.currentTimeMillis());
                    calendarSat.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
                    calendarSat.set(Calendar.HOUR_OF_DAY, hour);
                    calendarSat.set(Calendar.MINUTE, minute);
                    calendarSat.set(Calendar.SECOND, 0);

                    if (calendarSat.before(Calendar.getInstance())) {
                        calendarSat.add(Calendar.DATE, 7);
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                    editor.putLong("nextNotifyTime", (long) calendarSat.getTimeInMillis());
                    editor.apply();
                    PendingIntent pendingIntentSat = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);
                    diaryNotification(calendarSat,pendingIntentSat);


                }
            }

        });

        //파이어베이스
        FirebaseDatabase.getInstance().getReference()
                .child("tallenge")
                .child("alarm")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        AlarmData alarmData1= dataSnapshot.getValue(AlarmData.class);
                        alarmData.setSun(alarmData1.isSun());
                        alarmData.setMon(alarmData1.isMon());
                        alarmData.setTue(alarmData1.isTue());
                        alarmData.setWed(alarmData1.isWed());
                        alarmData.setThu(alarmData1.isThu());
                        alarmData.setFri(alarmData1.isFri());
                        alarmData.setSat(alarmData1.isSat());
                        sun.setChecked(alarmData.isSun());
                        mon.setChecked(alarmData.isMon());
                        thu.setChecked(alarmData.isThu());
                        wed.setChecked(alarmData.isWed());
                        tue.setChecked(alarmData.isTue());
                        fri.setChecked(alarmData.isFri());
                        sat.setChecked(alarmData.isSat());




                        /*
                        if (consumer == null)
                        {
                            //data does not exists for the specified key
                        }
                        else if(data.checked)
                        {
                            //data is checked
                        }
                        else {
                            //data not checked
                        }

                        */
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
    }


    void diaryNotification(Calendar calendar,PendingIntent pendingIntent)
    {
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean dailyNotify = sharedPref.getBoolean(SettingsActivity.KEY_PREF_DAILY_NOTIFICATION, true);
        //Boolean dailyNotify = true; // 무조건 알람을 사용

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        //Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            if (alarmManager != null) {

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*7, pendingIntent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                }
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);



        }
//        else { //Disable Daily Notifications
//            if (PendingIntent.getBroadcast(this, 0, alarmIntent, 0) != null && alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//                //Toast.makeText(this,"Notifications were disabled",Toast.LENGTH_SHORT).show();
//            }
//            pm.setComponentEnabledSetting(receiver,
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                    PackageManager.DONT_KILL_APP);
//        }
    }


}