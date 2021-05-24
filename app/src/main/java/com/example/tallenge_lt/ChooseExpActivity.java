//db 연동 실패

package com.example.tallenge_lt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallenge_lt.Catagory_item.Exp_item;
import com.example.tallenge_lt.Catagory_item.Hobby_item;
import com.example.tallenge_lt.Catagory_item.Lan_item;
import com.example.tallenge_lt.Catagory_item.Spo_item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseExpActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private ArrayList<ChooseExpData> arrayList;
    private ChooseExpAdapter chooseExpAdapter;
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private LinearLayoutManager linearLayoutManager;

    int REQUEST_IMAGE_CODE = 1001;
    private Button btn_back;

    private DatabaseReference myRef;
    private String TAG = "tag";
    private ArrayList<Expdata> arrayList1;
    private ArrayList<Exp_item> key1;
    private DatabaseReference categoryRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseStorage sref;



    private RecyclerView.Adapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private View view;
    private ImageView imageView;
    private TextView bc, sc, ctf;



    //private OnIntentReceived mIntentListener;
    // Create a storage reference from our app
    //StorageReference storageRef = storage.getReference();

    // Create a reference to "mountains.jpg"
    //StorageReference mountainsRef = storageRef.child("mountains.jpg");
    //StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(); //스토리지
    // Create a reference to 'images/mountains.jpg'
    //StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
    //StorageReference mExpRef = mStorageRef.child("expImage"); //프로필 스토리지 저장이름은 사용자 고유토큰과 스트링섞어서 만들었다.

// While the file names are the same, the references point to different files
//mountainsRef.getName().equals(mountainImagesRef.getName());    // true
//mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_exp);



        recyclerView = (RecyclerView)findViewById(R.id.ch_exp_rv);
        recyclerView.setHasFixedSize(true);  //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();  //객체 담을 어레이 리스트

        chooseExpAdapter = new ChooseExpAdapter(arrayList);
        //chooseExpAdapter = new ChooseExpAdapter(arrayList);

/*
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

        // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

 */



        String IdToken;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            IdToken = user.getUid();
            Log.d("idToken", IdToken);
        } else {
            // No user is signed in
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tallenge").child("CertifyImg").child(user.getUid());
        categoryRef = database.getReference("tallenge").child("UserAccount").child(user.getUid()).child("expdata");

        ChooseExpData chooseExpData = new ChooseExpData("123", "456", "789","1235");
        String parentCategory[] = {"Exp_item", "Hobby_item", "Lan_item", "Spo_item"};
        String childCategory[] = {"computer", "history", "math", "stock", "drawing", "music", "resin", "tarot", "chi", "eng", "jap", "sapn"
                , "badminton", "ht", "pilates", "swim"};

        ChooseExpData chooseExpData1 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                , "Exp_item", "computer", "");


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);
                //clear();
                //DataSnapshot snapshot dataSnapshot.getChildren();
                //DataSnapshot snapshot = (DataSnapshot) dataSnapshot.getChildren();

                int i = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   // int x = 0;
                    //Exp_item exp_item = snapshot.getValue(Exp_item.class);
                    //Hobby_item hobby_item = snapshot.getValue(Hobby_item.class);
                    if (i == 0) {
                        Exp_item exp_item = snapshot.getValue(Exp_item.class);
                        assert exp_item != null;
                        if (exp_item.getComputer().equals("true")) {
                            ChooseExpData chooseExpData1 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "전문분야", "컴퓨터", "");
                            myRef.child("컴퓨터").setValue(chooseExpData1);
                            arrayList.add(chooseExpData1);

                            //x = 1;
                        }
                        if (exp_item.getHistory().equals("true")) {
                            ChooseExpData chooseExpData2 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "전문분야", "역사", "");;
                            arrayList.add(chooseExpData2);
                            myRef.child("역사").setValue(chooseExpData2);
                            //arrayList.add(chooseExpData2);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (exp_item.getMath().equals("true")) {
                            ChooseExpData chooseExpData3 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "전문분야", "수학", "");;
                            arrayList.add(chooseExpData3);
                            myRef.child("수학").setValue(chooseExpData3);
                            //arrayList.add(chooseExpData3);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (exp_item.getStock().equals("true")) {
                            ChooseExpData chooseExpData4 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "전문분야", "주식", "");;
                            arrayList.add(chooseExpData4);
                            myRef.child("주식").setValue(chooseExpData4);
                            //arrayList.add(chooseExpData4);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }

                    }
                    if (i == 1) {
                        Hobby_item hobby_item = snapshot.getValue(Hobby_item.class);
                        assert hobby_item != null;
                        if (hobby_item.getDrawing().equals("true")) {
                            ChooseExpData chooseExpData5 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "취미", "그림", "");;
                            arrayList.add(chooseExpData5);
                            myRef.child("그림").setValue(chooseExpData5);
                            //arrayList.add(chooseExpData5);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (hobby_item.getMusic().equals("true")) {
                            ChooseExpData chooseExpData6 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "취미", "음악", "");;
                            arrayList.add(chooseExpData6);
                            myRef.child("수학").setValue(chooseExpData6);
                            //arrayList.add(chooseExpData6);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (hobby_item.getResin().equals("true")) {
                            ChooseExpData chooseExpData7 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "취미", "레진공예", "");;
                            arrayList.add(chooseExpData7);
                            myRef.child("레진공예").setValue(chooseExpData7);
                            //arrayList.add(chooseExpData7);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (hobby_item.getTarot().equals("true")) {
                            ChooseExpData chooseExpData8 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "취미", "타로", "");;
                            arrayList.add(chooseExpData8);
                            myRef.child("타로").setValue(chooseExpData8);
                            //arrayList.add(chooseExpData8);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }

                    }
                    if (i == 2) {
                        Lan_item lan_item = snapshot.getValue(Lan_item.class);
                        assert lan_item != null;
                        if (lan_item.getChi().equals("true")) {
                            ChooseExpData chooseExpData9 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "언어", "중국어", "");;
                            arrayList.add(chooseExpData9);
                            myRef.child("중국어").setValue(chooseExpData9);
                            //arrayList.add(chooseExpData9);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (lan_item.getEng().equals("true")) {
                            ChooseExpData chooseExpData10 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "언어", "영어", "");;
                            arrayList.add(chooseExpData10);
                            myRef.child("영어").setValue(chooseExpData10);
                            //arrayList.add(chooseExpData10);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (lan_item.getJap().equals("true")) {
                            ChooseExpData chooseExpData11 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "언어", "일본어", "");;
                            arrayList.add(chooseExpData11);
                            myRef.child("일본어").setValue(chooseExpData11);
                            //arrayList.add(chooseExpData11);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (lan_item.getSpan().equals("true")) {
                            ChooseExpData chooseExpData12 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "언어", "스페인어", "");;
                            arrayList.add(chooseExpData12);
                            myRef.child("스페인어").setValue(chooseExpData12);
                            //arrayList.add(chooseExpData12);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }

                    }
                    if (i == 3) {
                        Spo_item spo_item = snapshot.getValue(Spo_item.class);
                        assert spo_item != null;
                        if (spo_item.getBadminton().equals("true")) {
                            ChooseExpData chooseExpData13 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "스포츠", "배드민턴", "");;
                            arrayList.add(chooseExpData13);
                            myRef.child("배드민턴").setValue(chooseExpData13);
                            //arrayList.add(chooseExpData13);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (spo_item.getHT().equals("true")) {
                            ChooseExpData chooseExpData14 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "스포츠", "홈트", "");;
                            arrayList.add(chooseExpData14);
                            myRef.child("홈트").setValue(chooseExpData14);
                            //arrayList.add(chooseExpData14);
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (spo_item.getPilates().equals("true")) {
                            ChooseExpData chooseExpData14 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "스포츠", "필라테스", "");;
                            arrayList.add(chooseExpData14);
                            myRef.child("필라테스").setValue(chooseExpData14);
                            //arrayList.add(chooseExpData14);;
                            //chooseExpAdapter.notifyDataSetChanged();
                        }
                        if (spo_item.getSwim().equals("true")) {
                            ChooseExpData chooseExpData15 = new ChooseExpData("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/images.png?alt=media&token=4ae189ff-76f9-4c11-b0ce-9bc6621a9704"
                                    , "스포츠", "수영", "");;
                            arrayList.add(chooseExpData15);
                            myRef.child("수영").setValue(chooseExpData15);
                            //arrayList.add(chooseExpData15);;
                            //chooseExpAdapter.notifyDataSetChanged();
                        }

                    }
                    i++;

                }

                recyclerView.setAdapter(chooseExpAdapter);
                //Expdata expdata = dataSnapshot.getValue(Expdata.class);
                //


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }

        };

        categoryRef.addValueEventListener(postListener);


        btn_back = findViewById(R.id.btn_move);   //화면 이동 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseExpActivity.this, MyInfoActivity.class);
                startActivity(intent);  //액티비티 이동
            }
        });
        //
        // imageView = (ImageView) findViewById(R.id.ex_image);    //갤러리에서 사진 가져오기
        // imageView.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(in, REQUEST_IMAGE_CODE);
        //}
        //});
        // }
/*
        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CODE){
         Uri image = data.getData();
        try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
        imageView.setImageBitmap(bitmap);
         } catch (IOException e) {
        e.printStackTrace();
        }
         }

 */
    }

ImageView image1;
    String test1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CODE){
            Uri image = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
                chooseExpAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

}

